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
		Implicant[] imps = new Implicant[6];
		for(int i = 0;i < 6;i++)
		{
			imps[i] = new Implicant();
			imps[i].num = i;
		}
		Implicant[] assimps = new Implicant[3];
		for(int i = 0;i < 3;i++)
		{
			assimps[i] = new Implicant();
			assimps[i].num = 0;
		}
		assimps[0].difs.add(new Integer(1));
		assimps[1].difs.add(new Integer(2));
		assimps[2].difs.add(new Integer(4));
		a.add(imps[0]);
		b.add(imps[1]);
		b.add(imps[2]);
		b.add(imps[4]);
		Group res = Group.merge(a, b);
		assertEquals(res.myGroup.get(0).num, assimps[0].num);
		assertEquals(res.myGroup.get(0).difs, assimps[0].difs);
		assertEquals(res.myGroup.get(1).num, assimps[1].num);
		assertEquals(res.myGroup.get(1).difs, assimps[1].difs);
		assertEquals(res.myGroup.get(1).num, assimps[1].num);
		assertEquals(res.myGroup.get(1).difs, assimps[1].difs);
	}
}
