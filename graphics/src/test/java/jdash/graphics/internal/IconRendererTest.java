package jdash.graphics.internal;

import jdash.common.IconType;
import jdash.graphics.ColorSelection;
import jdash.graphics.IconRenderer;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static jdash.graphics.test.ImageTestUtils.assertImageEquals;
import static jdash.graphics.test.ImageTestUtils.loadTestImage;

class IconRendererTest {

    @Test
    void shouldReturnSpriteSheet() throws IOException {
        final var renderer = IconRenderer.load(IconType.CUBE, 1);
        assertImageEquals(loadTestImage("/icons/player_01-uhd.png"), renderer.getSpriteSheet());
    }

    @Test
    void shouldRenderCube1() throws IOException {
        final var renderer = IconRenderer.load(IconType.CUBE, 1);
        final var output = renderer.render(ColorSelection.defaultColors(false));
        assertImageEquals(loadTestImage("/tests/cube-1.png"), output);
    }

    @Test
    void shouldRenderShip10WithGlow() throws IOException {
        final var renderer = IconRenderer.load(IconType.SHIP, 10);
        final var output = renderer.render(ColorSelection.defaultColors(true));
        assertImageEquals(loadTestImage("/tests/ship-10.png"), output);
    }

    @Test
    void shouldRenderRobot1WithCustomColors() throws IOException {
        final var renderer = IconRenderer.load(IconType.ROBOT, 1);
        final var output = renderer.render(ColorSelection.of(16, 11, 14));
        assertImageEquals(loadTestImage("/tests/robot-1.png"), output);
    }

    @Test
    void shouldRenderSpider7() throws IOException {
        final var renderer = IconRenderer.load(IconType.SPIDER, 7);
        final var output = renderer.render(ColorSelection.defaultColors(false));
        assertImageEquals(loadTestImage("/tests/spider-7.png"), output);
    }

    @Test
    void shouldRenderSpider15WithCustomColors() throws IOException {
        final var renderer = IconRenderer.load(IconType.SPIDER, 15);
        final var output = renderer.render(ColorSelection.of(12, 9, 9));
        assertImageEquals(loadTestImage("/tests/spider-15.png"), output);
    }
}