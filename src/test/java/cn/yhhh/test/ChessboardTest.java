package cn.yhhh.test;

import static org.junit.Assert.*;
import org.junit.Test;

import cn.heroes.yellow.util.Chessboard;

public class ChessboardTest {

	@Test
	public void name2pos() {
		assertArrayEquals(new int[] { 1, 1 }, Chessboard.name2pos("A1"));
		assertArrayEquals(new int[] { 1, 2 }, Chessboard.name2pos("B1"));
		assertArrayEquals(new int[] { 2, 3 }, Chessboard.name2pos("C2"));
	}
	
	@Test
	public void pos2name() {
		assertEquals("A1", Chessboard.pos2name(1, 1));
		assertEquals("B1", Chessboard.pos2name(1, 2));
		assertEquals("C2", Chessboard.pos2name(2, 3));
	}
}
