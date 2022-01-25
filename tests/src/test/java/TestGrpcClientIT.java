import com.cloudbeds.avro.AddressData;
import com.cloudbeds.avro.UserAddressService;
import com.cloudbeds.avro.UserData;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.apache.avro.grpc.AvroGrpcClient;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TestGrpcClientIT {

    private static UserAddressService stub;
    private static ManagedChannel channel;

    @BeforeAll
    static void setUp() {
        setUpServerAndClient();
        createUser();
    }

    static void setUpServerAndClient() {
        channel = ManagedChannelBuilder.forAddress("localhost", 9091).usePlaintext().build();
        stub = AvroGrpcClient.create(channel, UserAddressService.class);
    }

    static void createUser() {
        final var addressData = AddressData.newBuilder()
                .setId(0L)
                .setFirstAddress("first address")
                .setSecondAddress("second address")
                .setCity("city")
                .setState("state")
                .setCountry("country")
                .setZip("zip")
                .build();

        final var userData = UserData.newBuilder()
                .setId(0L)
                .setFirstName("first name")
                .setLastName("last name")
                .setEmail("first-name@email.com")
                .setPassword("123456")
                .setAddresses(List.of(addressData))
                .build();

        var userdata = stub.Create(userData);
        System.out.println("Creating User...");
        System.out.println(userdata);
    }

    @AfterAll
    static void cleanUp() {
        channel.shutdownNow();
    }


    @Test
    public void testUserInfoRetrieved() {
        System.out.println("Retrieving the user...");
        final var userInfo = stub.RetrieveInfo(1L);
        System.out.println(userInfo);
        assertNotNull(userInfo.getBody());
        assertEquals("OK", userInfo.getStatus().toString());
        assertEquals(1, userInfo.getBody().getId());
        assertNotEquals(0, userInfo.getBody().getAddresses().size());
    }

    @Test
    public void testNotFoundUserInfo() {
        System.out.println("Retrieving the user...");
        final var userInfo = stub.RetrieveInfo(-1000L);
        System.out.println(userInfo);
        assertNull(userInfo.getBody());
        assertEquals("NOT_FOUND", userInfo.getStatus().toString());
    }
}
