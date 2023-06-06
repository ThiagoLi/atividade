package inf300.domain;
import org.junit.Assert;
import org.junit.Test;

public class StateTest {
	   @Test
	    public void testGetInfo() {
	        State state = new State() {
	            @Override
	            public String getInfo() {
	                return "Info";
	            }

	            @Override
	            public String getGroupState() {
	                return "GroupState";
	            }

	            @Override
	            public String name() {
	                return "StateName";
	            }
	        };

	        String expectedInfo = "Info";
	        String actualInfo = state.getInfo();
	        Assert.assertEquals(expectedInfo, actualInfo);
	    }

	    @Test
	    public void testGetGroupState() {
	        State state = new State() {
	            @Override
	            public String getInfo() {
	                return "Info";
	            }

	            @Override
	            public String getGroupState() {
	                return "GroupState";
	            }

	            @Override
	            public String name() {
	                return "StateName";
	            }
	        };

	        String expectedGroupState = "GroupState";
	        String actualGroupState = state.getGroupState();
	        Assert.assertEquals(expectedGroupState, actualGroupState);
	    }

	    @Test
	    public void testName() {
	        State state = new State() {
	            @Override
	            public String getInfo() {
	                return "Info";
	            }

	            @Override
	            public String getGroupState() {
	                return "GroupState";
	            }

	            @Override
	            public String name() {
	                return "StateName";
	            }
	        };

	        String expectedName = "StateName";
	        String actualName = state.name();
	        Assert.assertEquals(expectedName, actualName);
	    }
}
