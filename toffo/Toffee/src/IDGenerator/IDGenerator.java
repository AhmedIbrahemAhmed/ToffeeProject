package IDGenerator;

import java.util.UUID;

public class IDGenerator {
    // generate IDs using UUID standard
    public long generate() {
        UUID uuid = UUID.randomUUID();
        String randomUUIDString = uuid.toString().replace("-", "");
        long randomNumber = Long.parseLong(randomUUIDString.substring(0, 14), 16);
        return randomNumber;
    }
}
