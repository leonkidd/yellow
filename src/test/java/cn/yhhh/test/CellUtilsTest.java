package cn.yhhh.test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import cn.heroes.yellow.util.CellUtils;

public class CellUtilsTest {

	@Test
	public void name2pos() {
		assertArrayEquals(new int[] { 1, 1 }, CellUtils.name2pos("A1"));
		assertArrayEquals(new int[] { 1, 2 }, CellUtils.name2pos("B1"));
		assertArrayEquals(new int[] { 2, 3 }, CellUtils.name2pos("C2"));
	}
	
	@Test
	public void pos2name() {
		assertEquals("A1", CellUtils.pos2name(1, 1));
		assertEquals("B1", CellUtils.pos2name(1, 2));
		assertEquals("C2", CellUtils.pos2name(2, 3));
	}
}
