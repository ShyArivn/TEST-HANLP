package hanlp.code;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class CodeTest extends TestCase
{
    public void testDecode( )
    {
		String str= Code.decode("\u9098");
		assertEquals(1, 1);
    }

}
