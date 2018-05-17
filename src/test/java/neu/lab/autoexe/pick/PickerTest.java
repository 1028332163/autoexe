package neu.lab.autoexe.pick;


import org.junit.Before;
import org.junit.Test;


public class PickerTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		try {
			new Picker().pick("D:\\ws\\gitHub_old\\activemq-cpp-activemq-cpp-pom-3.1.0-RC1\\activemq-cpp-openwire-generator\\pom.xml");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
