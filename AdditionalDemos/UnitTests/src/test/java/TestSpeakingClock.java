import static org.junit.Assert.assertEquals;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
@RunWith(Parameterized.class)
public class TestSpeakingClock {
	
	private SpeakingClock clock;
	
	private int hour, minute;
	private String result;
	
	
	@Before
	public void setup() {
		clock = new SpeakingClock();
	}
	
	
	public TestSpeakingClock(int hour, int minute, String result) {
		this.hour = hour;
		this.minute = minute;
		this.result = result;
	}
	
	@Parameters
	public static Collection getParameters() {
		return Arrays.asList(new Object[][]{
			{0,0,"midnight"},
			{12,0,"midday"}
			
		});
	}
	
	@Test
	public void canDoDifferentTimes() {
		assertEquals(result, clock.getTimeAsText(LocalTime.of(hour, minute)));
	}
	
	
	
	@Test
	@Ignore
	public void canDoMidnight() {
		String textTime = clock.getTimeAsText(LocalTime.MIDNIGHT);
		assertEquals("midnight", textTime);
	}
	
	
	

}
