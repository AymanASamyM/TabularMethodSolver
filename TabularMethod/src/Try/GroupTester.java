package Try;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Test;

import firstStep.Group;
import firstStep.Implicant;

public class GroupTester {
	
	@Test
	public void test1()
	{
		/* 1stImps	2ndImps		3rdImps
		 * 0>a	c	0(1)>ab	c	0(1,2)>abc
		 * _		0(2)>ab	c	0(1,4)>abc
		 * 1>b	c	0(4)>ab	c	
		 * 2>b	c	____	
		 * 4>b	c	1(2)>bc c
		 * _		1(4)>bc c
		 * 3>c	c	2(1)>bc c
		 * 5>c	c	4(1)>bc c
		 * 
		 * a,b,c,ab,bc & abc are groups
		 * and c stands for checked
		 */
		
		Implicant[] firstImps = new Implicant[6];
		for(int i = 0;i < 6;i++)
		{
			firstImps[i] = new Implicant(i);
		}
		
		Implicant[] secondImps = new Implicant[7];
		secondImps[0] = new Implicant(0);
		secondImps[0].addDifference(1);
		secondImps[1] = new Implicant(0);
		secondImps[1].addDifference(2);
		secondImps[2] = new Implicant(0);
		secondImps[2].addDifference(4);
		secondImps[3] = new Implicant(1);
		secondImps[3].addDifference(2);
		secondImps[4] = new Implicant(1);
		secondImps[4].addDifference(4);
		secondImps[5] = new Implicant(2);
		secondImps[5].addDifference(1);
		secondImps[6] = new Implicant(4);
		secondImps[6].addDifference(1);
		
		Implicant[] thirdImps = new Implicant[2];
		thirdImps[0] = new Implicant(0);
		thirdImps[0].addDifference(1);
		thirdImps[0].addDifference(2);
		thirdImps[1] = new Implicant(0);
		thirdImps[1].addDifference(1);
		thirdImps[1].addDifference(4);
		
		Group a = new Group();
		a.add(firstImps[0]);
		Group b = new Group();
		b.add(firstImps[1]);
		b.add(firstImps[2]);
		b.add(firstImps[4]);
		Group c = new Group();
		c.add(firstImps[3]);
		c.add(firstImps[5]);
		
		Group ab = Group.merge(a, b);
		assertEquals(new LinkedList<Implicant>(), a.getPrimeImplicants());
		assertEquals(new LinkedList<Implicant>(), b.getPrimeImplicants());
		assertEquals(ab.get(0), secondImps[0]);
		assertEquals(ab.get(1), secondImps[1]);
		assertEquals(ab.get(2), secondImps[2]);
		
		Group bc = Group.merge(b, c);
		assertEquals(new LinkedList<Implicant>(), c.getPrimeImplicants());
		assertEquals(bc.get(0), secondImps[3]);
		assertEquals(bc.get(1), secondImps[4]);
		assertEquals(bc.get(2), secondImps[5]);
		assertEquals(bc.get(3), secondImps[6]);
		
		Group abc = Group.merge(ab, bc);
		assertEquals(new LinkedList<Implicant>(), ab.getPrimeImplicants());
		assertEquals(new LinkedList<Implicant>(), bc.getPrimeImplicants());
		LinkedList<Implicant> abcNonChecked = new LinkedList<Implicant>();
		abcNonChecked.add(thirdImps[0]);
		abcNonChecked.add(thirdImps[1]);
		assertEquals(abcNonChecked, abc.getPrimeImplicants());
		assertEquals(abc.get(0), thirdImps[0]);
		assertEquals(abc.get(1), thirdImps[1]);
	}
	
	@Test
	public void test2()
	{
		/*
		 * 1stImps	2ndImps			3rdImps			4thImps		
		 * 0 >a	c	0 (2) >ab c		0(2,4) >abc	c	0(2,4,8) >abcd
		 * _		0 (4) >ab c		0(2,8) >abc c
		 * 2 >b	c	0 (8) >ab c		0(2,16)>abc
		 * 4 >b	c	0 (16)>ab c		0(4,8) >abc c
		 * 8 >b	c	____			_______
		 * 16>b	c	2 (4) >bc c		2(4,8) >bcd c
		 * _		2 (8) >bc c		4(2,8) >bcd c
		 * 6 >c	c	2 (16)>bc c		8(1,2) >bcd
		 * 9 >c	c	4 (2) >bc c		8(1,4) >bcd
		 * 10>c	c	4 (8) >bc c		8(2,4) >bcd c
		 * 12>c	c	8 (1) >bc c	
		 * 18>c	c	8 (2) >bc c	
		 * _		8 (4) >bc c	
		 * 7 >d	c	16(2) >bc c
		 * 11>d	c	____		
		 * 13>d	c	6 (1) >cd 	
		 * 14>d	c	6 (8) >cd c	
		 * 19>d	c	9 (2) >cd c
		 * 21>d	c	9 (4) >cd c
		 * _		10(1) >cd c
		 * 29>e	c	10(4) >cd c
		 * 30>e	c	12(1) >cd c	
		 * 			12(2) >cd c	
		 * 			18(1) >cd 	
		 * 			_____		
		 * 			13(16) >de 
		 * 			14(16) >de 	
		 * 			21(8)  >de 
		 * */
		Implicant[] firstImps = new Implicant[32];
		for(int i = 0;i < 32;i++)
		{
			firstImps[i] = new Implicant(i);
		}
		
		Implicant[] secondImps = new Implicant[26];
		secondImps[0] = new Implicant(0);
		secondImps[0].addDifference(2);
		secondImps[1] = new Implicant(0);
		secondImps[1].addDifference(4);
		secondImps[2] = new Implicant(0);
		secondImps[2].addDifference(8);
		secondImps[3] = new Implicant(0);
		secondImps[3].addDifference(16);
		secondImps[4] = new Implicant(2);
		secondImps[4].addDifference(4);
		secondImps[5] = new Implicant(2);
		secondImps[5].addDifference(8);
		secondImps[6] = new Implicant(2);
		secondImps[6].addDifference(16);
		secondImps[7] = new Implicant(4);
		secondImps[7].addDifference(2);
		secondImps[8] = new Implicant(4);
		secondImps[8].addDifference(8);
		secondImps[9] = new Implicant(8);
		secondImps[9].addDifference(1);
		secondImps[10] = new Implicant(8);
		secondImps[10].addDifference(2);
		secondImps[11] = new Implicant(8);
		secondImps[11].addDifference(4);
		secondImps[12] = new Implicant(16);
		secondImps[12].addDifference(2);
		secondImps[13] = new Implicant(6);
		secondImps[13].addDifference(1);
		secondImps[14] = new Implicant(6);
		secondImps[14].addDifference(8);
		secondImps[15] = new Implicant(9);
		secondImps[15].addDifference(2);
		secondImps[16] = new Implicant(9);
		secondImps[16].addDifference(4);
		secondImps[17] = new Implicant(10);
		secondImps[17].addDifference(1);
		secondImps[18] = new Implicant(10);
		secondImps[18].addDifference(4);
		secondImps[19] = new Implicant(12);
		secondImps[19].addDifference(1);
		secondImps[20] = new Implicant(12);
		secondImps[20].addDifference(2);
		secondImps[21] = new Implicant(18);
		secondImps[21].addDifference(1);
		secondImps[22] = new Implicant(13);
		secondImps[22].addDifference(16);
		secondImps[23] = new Implicant(14);
		secondImps[23].addDifference(16);
		secondImps[24] = new Implicant(21);
		secondImps[24].addDifference(8);
		
		Implicant[] thirdImps = new Implicant[9];
		thirdImps[0] = new Implicant(0);
		thirdImps[0].addDifference(2);
		thirdImps[0].addDifference(4);
		thirdImps[1] = new Implicant(0);
		thirdImps[1].addDifference(2);
		thirdImps[1].addDifference(8);
		thirdImps[2] = new Implicant(0);
		thirdImps[2].addDifference(2);
		thirdImps[2].addDifference(16);
		thirdImps[3] = new Implicant(0);
		thirdImps[3].addDifference(4);
		thirdImps[3].addDifference(8);
		thirdImps[4] = new Implicant(2);
		thirdImps[4].addDifference(4);
		thirdImps[4].addDifference(8);
		thirdImps[5] = new Implicant(4);
		thirdImps[5].addDifference(2);
		thirdImps[5].addDifference(8);
		thirdImps[6] = new Implicant(8);
		thirdImps[6].addDifference(1);
		thirdImps[6].addDifference(2);
		thirdImps[7] = new Implicant(8);
		thirdImps[7].addDifference(1);
		thirdImps[7].addDifference(4);
		thirdImps[8] = new Implicant(8);
		thirdImps[8].addDifference(2);
		thirdImps[8].addDifference(4);
		
		Implicant forthImp = new Implicant(0);
		forthImp.addDifference(2);
		forthImp.addDifference(4);
		forthImp.addDifference(8);
		
		Group a = new Group();
		a.add(firstImps[0]);
		
		Group b= new Group();
		b.add(firstImps[2]);
		b.add(firstImps[4]);
		b.add(firstImps[8]);
		b.add(firstImps[16]);
		
		Group c = new Group();
		c.add(firstImps[6]);
		c.add(firstImps[9]);
		c.add(firstImps[10]);
		c.add(firstImps[12]);
		c.add(firstImps[18]);
		
		Group d = new Group();
		d.add(firstImps[7]);
		d.add(firstImps[11]);
		d.add(firstImps[13]);
		d.add(firstImps[14]);
		d.add(firstImps[19]);
		d.add(firstImps[21]);
		
		Group e = new Group();
		e.add(firstImps[29]);
		e.add(firstImps[30]);
		
		Group ab = Group.merge(a, b);
		assertEquals(new LinkedList<Implicant>(), a.getPrimeImplicants());
		assertEquals(4, ab.size());
		assertEquals(secondImps[0], ab.get(0));
		assertEquals(secondImps[1], ab.get(1));
		assertEquals(secondImps[2], ab.get(2));
		assertEquals(secondImps[3], ab.get(3));
		
		Group bc = Group.merge(b, c);
		assertEquals(new LinkedList<Implicant>(), b.getPrimeImplicants());
		assertEquals(9, bc.size());
		assertEquals(secondImps[4], bc.get(0));
		assertEquals(secondImps[5], bc.get(1));
		assertEquals(secondImps[6], bc.get(2));
		assertEquals(secondImps[7], bc.get(3));
		assertEquals(secondImps[8], bc.get(4));
		assertEquals(secondImps[9], bc.get(5));
		assertEquals(secondImps[10], bc.get(6));
		assertEquals(secondImps[11], bc.get(7));
		assertEquals(secondImps[12], bc.get(8));
		
		Group cd = Group.merge(c, d);
		assertEquals(new LinkedList<Implicant>(), c.getPrimeImplicants());
		assertEquals(9, cd.size());
		assertEquals(secondImps[13], cd.get(0));
		assertEquals(secondImps[14], cd.get(1));
		assertEquals(secondImps[15], cd.get(2));
		assertEquals(secondImps[16], cd.get(3));
		assertEquals(secondImps[17], cd.get(4));
		assertEquals(secondImps[18], cd.get(5));
		assertEquals(secondImps[19], cd.get(6));
		assertEquals(secondImps[20], cd.get(7));
		assertEquals(secondImps[21], cd.get(8));
		
		Group de = Group.merge(d, e);
		assertEquals(new LinkedList<Implicant>(), d.getPrimeImplicants());
		assertEquals(new LinkedList<Implicant>(), e.getPrimeImplicants());
		assertEquals(3, de.size());
		assertEquals(secondImps[22], de.get(0));
		assertEquals(secondImps[23], de.get(1));
		assertEquals(secondImps[24], de.get(2));
		
		Group abc = Group.merge(ab, bc);
		assertEquals(new LinkedList<Implicant>(), ab.getPrimeImplicants());
		assertEquals(4, abc.size());
		assertEquals(thirdImps[0], abc.get(0));
		assertEquals(thirdImps[1], abc.get(1));
		assertEquals(thirdImps[2], abc.get(2));
		assertEquals(thirdImps[3], abc.get(3));
		
		Group bcd = Group.merge(bc, cd);
		assertEquals(new LinkedList<Implicant>(), bc.getPrimeImplicants());
		assertEquals(5, bcd.size());
		assertEquals(thirdImps[4], bcd.get(0));
		assertEquals(thirdImps[5], bcd.get(1));
		assertEquals(thirdImps[6], bcd.get(2));
		assertEquals(thirdImps[7], bcd.get(3));
		assertEquals(thirdImps[8], bcd.get(4));
		
		Group cde = Group.merge(cd, de);
		LinkedList<Implicant> cdNonChecked = new LinkedList<Implicant>();
		cdNonChecked.add(secondImps[13]);
		cdNonChecked.add(secondImps[21]);
		assertEquals(cdNonChecked, cd.getPrimeImplicants());
		LinkedList<Implicant> deNonChecked = new LinkedList<Implicant>();
		deNonChecked.add(secondImps[22]);
		deNonChecked.add(secondImps[23]);
		deNonChecked.add(secondImps[24]);
		assertEquals(deNonChecked, de.getPrimeImplicants());
		assertEquals(0, cde.size());
		
		Group abcd = Group.merge(abc, bcd);
		LinkedList<Implicant> abcNonChecked = new LinkedList<Implicant>();
		abcNonChecked.add(thirdImps[2]);
		assertEquals(abcNonChecked, abc.getPrimeImplicants());
		LinkedList<Implicant> bcdNonChecked = new LinkedList<Implicant>();
		bcdNonChecked.add(thirdImps[6]);
		bcdNonChecked.add(thirdImps[7]);
		assertEquals(bcdNonChecked, bcd.getPrimeImplicants());
		LinkedList<Implicant> abcdNonChecked = new LinkedList<Implicant>();
		abcdNonChecked.add(forthImp);
		assertEquals(abcdNonChecked, abcd.getPrimeImplicants());
		assertEquals(1, abcd.size());
		assertEquals(forthImp, abcd.get(0));
	}
}
