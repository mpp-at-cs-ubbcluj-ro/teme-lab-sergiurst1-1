import org.example.model.ComputerRepairRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ComputerRepairRequestTest {
    
    @Test
    @DisplayName("First Test")
    public void firstTest(){
        ComputerRepairRequest request = new ComputerRepairRequest();
        assertEquals("", request.getOwnerName());
        assertEquals("", request.getOwnerAddress());
    }

    @Test
    @DisplayName("Second Test")
    public void secondTest(){
        assertEquals(2,2,"Numerele ar trebui sa fie egale!");
    }
      
}
