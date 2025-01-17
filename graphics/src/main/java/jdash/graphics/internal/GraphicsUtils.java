package jdash.graphics.internal;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.FilteredImageSource;
import java.awt.image.RGBImageFilter;
import java.util.List;

import static jdash.graphics.internal.AnimationFrame.ICON_HEIGHT;
import static jdash.graphics.internal.AnimationFrame.ICON_WIDTH;

public final class GraphicsUtils {

    public static Point2D.Double parsePoint(String tupleStr) {
        return Tuple.parse(tupleStr, Double::parseDouble).as(Point2D.Double::new);
    }

    public static Dimension parseDimension(String tupleStr) {
        return Tuple.parse(tupleStr, s -> (int) Math.ceil(Double.parseDouble(s))).as(Dimension::new);
    }

    public static Rectangle2D.Double parseRectangle(String tupleStr) {
        String[] split = tupleStr.substring(2, tupleStr.length() - 2).split("}?,\\{?");
        return new Rectangle2D.Double(Double.parseDouble(split[0]), Double.parseDouble(split[1]),
                Double.parseDouble(split[2]), Double.parseDouble(split[3]));
    }

    public static Image applyColor(Image img, Color color) {
        if (color == null) return img;
        return Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(img.getSource(), new RGBImageFilter() {
            @Override
            public int filterRGB(int x, int y, int rgb) {
                return rgb & color.getRGB();
            }
        }));
    }

    public static Image reduceBrightness(Image img) {
        return Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(img.getSource(), new RGBImageFilter() {
            @Override
            public int filterRGB(int x, int y, int rgb) {
                return rgb & 0xFF808080;
            }
        }));
    }

    public static BufferedImage renderLayers(List<? extends Renderable> layers,
                                             BufferedImage spriteSheet, RenderFilter filter) {
        final var image = new BufferedImage(ICON_WIDTH, ICON_HEIGHT, BufferedImage.TYPE_INT_ARGB);
        final var g = image.createGraphics();
        layers.forEach(layer -> g.drawImage(layer.render(spriteSheet, filter),
                (ICON_WIDTH - layer.getWidth()) / 2, (ICON_HEIGHT - layer.getHeight()) / 2, null));
        g.dispose();
        return image;
    }

    @SuppressWarnings("SuspiciousNameCombination")
    public static BufferedImage trim(BufferedImage source) {
        final var w = source.getWidth() - 1;
        final var h = source.getHeight() - 1;
        final var x1 = indexOfLastEmptyRow(source, 0, w, 0, h, false);
        final var y1 = indexOfLastEmptyRow(source, 0, h, x1, w, true);
        final var x2 = indexOfLastEmptyRow(source, w, x1, y1, h, false);
        final var y2 = indexOfLastEmptyRow(source, h, y1, x1, x2, true);
        return source.getSubimage(x1, y1, x2 - x1, y2 - y1);
    }

    private static int indexOfLastEmptyRow(BufferedImage source, int fromX, int toX, int fromY, int toY,
                                           boolean swapped) {
        final var factor = fromX < toX ? 1 : -1;
        for (var x = fromX * factor; x < toX * factor; x++) {
            final var realX = x / factor;
            var isRowEmpty = true;
            for (var y = fromY; y < toY; y++) {
                if (source.getRGB(swapped ? y : realX, swapped ? realX : y) != 0) {
                    isRowEmpty = false;
                    break;
                }
            }
            if (!isRowEmpty) {
                return realX;
            }
        }
        return fromX;
    }
}
