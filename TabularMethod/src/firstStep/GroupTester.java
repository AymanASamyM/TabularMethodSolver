package firstStep;

import static org.junit.Assert.*;

import org.junit.Test;

public class GroupTester {
	
	
	@Test
	public void testCompareDifs() {
		Implicant a = new Implicant();
		Implicant b = new Implicant();
		for(int i = 0;i < 10;i++)
		{
			a.difs.add(new Integer(i));
			b.difs.add(new Integer(i));
		}
		assertTrue(Group.compareDifs(a, b));
		a.difs.add(new Integer(0));
		assertFalse(Group.compareDifs(a, b));
	}
	
	@Test
	public void testMerge()
	{
		Group a = new Group();
		Group b = new Group();
		Group c = new Group();
		Implicant[] imps = new Implicant[6];
		for(int i = 0;i < 6;i++)
		{
			imps[i] = new Implicant();
			imps[i].num = i;
		}
		Implicant[] assimps = new Implicant[7];
		for(int i = 0;i < 3;i++)
		{
			assimps[i] = new Implicant(0);
		}
		assimps[3] = new Implicant(1);
		assimps[4] = new Implicant(1);
		assimps[5] = new Implicant(2);
		assimps[6] = new Implicant(4);
		assimps[0].difs.add(new Integer(1));
		assimps[1].difs.add(new Integer(2));
		assimps[2].difs.add(new Integer(4));
		assimps[3].difs.add(new Integer(2));
		assimps[4].difs.add(new Integer(4));
		assimps[5].difs.add(new Integer(1));
		assimps[6].difs.add(new Integer(1));
		a.add(imps[0]);
		b.add(imps[1]);
		b.add(imps[2]);
		b.add(imps[4]);
		c.add(imps[3]);
		c.add(imps[5]);
		Implicant[] assimps1 = new Implicant[2];
		assimps1[0] = new Implicant(0);
		assimps1[1] = new Implicant(0);
		assimps1[0].difs.add(new Integer(1));
		assimps1[0].difs.add(new Integer(2));
		assimps1[1].difs.add(new Integer(1));
		assimps1[1].difs.add(new Integer(4));
		Group res1 = Group.merge(a, b);
		Group res2 = Group.merge(b, c);
		Group res3 = Group.merge(res1, res2);
		assertEquals(res1.myGroup.get(0), assimps[0]);
		assertEquals(res1.myGroup.get(1), assimps[1]);
		assertEquals(res1.myGroup.get(2), assimps[2]);
		assertEquals(res2.myGroup.get(0), assimps[3]);
		assertEquals(res2.myGroup.get(1), assimps[4]);
		assertEquals(res2.myGroup.get(2), assimps[5]);
		assertEquals(res2.myGroup.get(3), assimps[6]);
		assertEquals(res3.myGroup.get(0), assimps1[0]);
		assertEquals(res3.myGroup.get(1), assimps1[1]);
	}
}
