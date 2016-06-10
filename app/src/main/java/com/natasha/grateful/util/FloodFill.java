
package com.natasha.grateful.util;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class FloodFill {
	public interface PixelMatcher {

		boolean match(int x, int y);
	}

	public interface PixelSetter {

		void set(int x1, int x2, int y);
	}

	public static void fill(int x, int y, PixelMatcher matcher, PixelSetter setter) {
		Queue<Pixel> queue = new LinkedList<Pixel>();
		queue.add(new Pixel(x, y));
		while (!queue.isEmpty()) {
			Pixel p = queue.remove();
			int px1 = p._x;
			int px2 = p._x;
			int py = p._y;
			if (matcher.match(px1, py)) {
				while (matcher.match(px1, py))
					px1--;
				px1++;
				while (matcher.match(px2, py))
					px2++;
				boolean prevMatchUp = false;
				boolean prevMatchDn = false;
				setter.set(px1, px2, py);
				for (int px = px1; px < px2; px++) {
					boolean matchUp = matcher.match(px, py - 1);
					if (matchUp && !prevMatchUp)
						queue.add(new Pixel(px, py - 1));
					boolean matchDn = matcher.match(px, py + 1);
					if (matchDn && !prevMatchDn)
						queue.add(new Pixel(px, py + 1));
					prevMatchUp = matchUp;
					prevMatchDn = matchDn;
				}
			}
		}
	}


	public static void fillRaw(int x, int y, int width, int height, byte[] mask,
			int[] pixels, int color) {
		Queue<Pixel> queue = new LinkedList<Pixel>();
		queue.add(new Pixel(x, y));
		while (!queue.isEmpty()) {
			Pixel p = queue.remove();
			int px1 = p._x;
			int px2 = p._x;
			int py = p._y;
			int pp = py * width;

			if (mask[pp + px1] != 0) {
				while (px1 >= 0 && mask[pp + px1] != 0)
					px1--;
				px1++;
				while (px2 < width && mask[pp + px2] != 0)
					px2++;
				Arrays.fill(pixels, pp + px1, pp + px2, color);
				Arrays.fill(mask, pp + px1, pp + px2, (byte) 0);
				boolean prevMatchUp = false;
				boolean prevMatchDn = false;
				int ppUp = pp - width;
				int ppDn = pp + width;
				for (int px = px1; px < px2; px++) {
					if (py > 0) {
						boolean matchUp = mask[ppUp + px] != 0;
						if (matchUp && !prevMatchUp)
							queue.add(new Pixel(px, py - 1));
						prevMatchUp = matchUp;
					}
					if (py + 1 < height) {
						boolean matchDn = mask[ppDn + px] != 0;
						if (matchDn && !prevMatchDn)
							queue.add(new Pixel(px, py + 1));
						prevMatchDn = matchDn;
					}
				}
			}
		}
	}

	private static class Pixel {
		public Pixel(int x, int y) {
			_x = x;
			_y = y;
		}

		public int _x;
		public int _y;
	}
}
