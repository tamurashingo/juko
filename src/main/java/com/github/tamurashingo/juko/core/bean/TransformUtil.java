/*-
 * The MIT License
 * 
 * Copyright (c) 2002, 2013 tamura shingo
 * 
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject
 * to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS
 * OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL
 * THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR
 * OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
 * ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE
 * OR OTHER DEALINGS IN THE SOFTWARE.
 * 
 */
package com.github.tamurashingo.juko.core.bean;

/**
 * this class provides static methods for manipulating {@code Transofm}.
 * 
 * @author tamura shingo
 *
 */
public class TransformUtil {

	/**
	 * no instance.
	 */
	private TransformUtil() {
	}
	
	/**
	 * create rotation matrix about the x axis.
	 * <table style="text-align:center; font-style:italic; border-left:1px solid black; border-right:1px solid black;">
	 * <tr><td>1</td><td>0</td><td>0</td><td>0<td></tr>
	 * <tr><td>0</td><td>cos&#952;</td><td>-sin&#952;</td><td>0<td></tr>
	 * <tr><td>0</td><td>sin&#952;</td><td>cos&#952;</td><td>0<td></tr>
	 * <tr><td>0</td><td>0</td><td>0</td><td>1<td></tr>
	 * </table>
	 * @param rad radian
	 * @return
	 */
	public static Transform createRotateX(double rad) {
		double sin = Math.sin(rad);
		double cos = Math.cos(rad);

		double[][] rotx = {
				{ 1,   0,    0, 0 },
				{ 0, cos, -sin, 0 },
				{ 0, sin,  cos, 0 },
				{ 0,   0,    0, 1 },
		};
		
		return new Transform(rotx);
	}
	

	/**
	 * create rotation matrix about the y axis.
	 * <table style="text-align:center; font-style:italic; border-left:1px solid black; border-right:1px solid black;">
	 * <tr><td>cos&#952;</td><td>0</td><td>sin&#952;</td><td>0<td></tr>
	 * <tr><td>0</td><td>1</td><td>0</td><td>0<td></tr>
	 * <tr><td>-sin&#952;</td><td>0</td><td>cos&#952;</td><td>0<td></tr>
	 * <tr><td>0</td><td>0</td><td>0</td><td>1<td></tr>
	 * </table>
	 * @param rad radian
	 * @return
	 */
	public static Transform createRotateY(double rad) {
		double sin = Math.sin(rad);
		double cos = Math.cos(rad);
		
		double[][] roty = {
				{  cos, 0, sin, 0 },
				{    0, 1,   0, 0 },
				{ -sin, 0, cos, 0 },
				{    0, 0,   0, 1 },
		};
		
		return new Transform(roty);
	}
	
	/**
	 * create rotation matrix about the z axis.
	 * <table style="text-align:center; font-style:italic; border-left:1px solid black; border-right:1px solid black;">
	 * <tr><td>cos&#952;</td><td>-sin&#952;</td><td>0</td><td>0<td></tr>
	 * <tr><td>sin&#952;</td><td>cos&#952;</td><td>0</td><td>0<td></tr>
	 * <tr><td>0</td><td>0</td><td>1</td><td>0<td></tr>
	 * <tr><td>0</td><td>0</td><td>0</td><td>1<td></tr>
	 * </table>
	 * @param rad radian
	 * @return
	 */
	public static Transform createRotateZ(double rad) {
		double sin = Math.sin(rad);
		double cos = Math.cos(rad);
		
		double[][] rotz = {
				{ cos, -sin, 0, 0 },
				{ sin,  cos, 0, 0 },
				{   0,    0, 1, 0 },
				{   0,    0, 0, 1 },
		};

		return new Transform(rotz);
	}
	
	
	/**
	 * create translation matrix. 
	 * <table style="text-align:center; font-style:italic; border-left:1px solid black; border-right:1px solid black;">
	 * <tr><td>1</td><td>0</td><td>0</td><td>x<td></tr>
	 * <tr><td>0</td><td>1</td><td>0</td><td>y<td></tr>
	 * <tr><td>0</td><td>0</td><td>1</td><td>z<td></tr>
	 * <tr><td>0</td><td>0</td><td>0</td><td>1<td></tr>
	 * </table>
	 * @param x
	 * @param y
	 * @param z
	 * @return
	 */
	public static Transform createTranslate(double x, double y, double z) {
		double[][] t = {
				{ 1, 0, 0, x },
				{ 0, 1, 0, y },
				{ 0, 0, 1, z },
				{ 0, 0, 0, 1 },
		};
		return new Transform(t);
	}
	
	/**
	 * create scaling matrix.
	 * <table style="text-align:center; font-style:italic; border-left:1px solid black; border-right:1px solid black;">
	 * <tr><td>x</td><td>0</td><td>0</td><td>0<td></tr>
	 * <tr><td>0</td><td>y</td><td>0</td><td>0<td></tr>
	 * <tr><td>0</td><td>0</td><td>z</td><td>0<td></tr>
	 * <tr><td>0</td><td>0</td><td>0</td><td>1<td></tr>
	 * </table>
	 * @param x
	 * @param y
	 * @param z
	 * @return
	 */
	public static Transform createScale(double x, double y, double z) {
		double[][] s = {
				{ x, 0, 0, 0 },
				{ 0, y, 0, 0 },
				{ 0, 0, z, 0 },
				{ 0, 0, 0, 1 },
		};
		return new Transform(s);
	}
}
