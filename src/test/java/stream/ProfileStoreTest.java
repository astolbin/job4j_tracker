package stream;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ProfileStoreTest {
    @Test
    public void whenExtractAddressCollection() {
        List<Profile> profiles = new ArrayList<>();
        Address address = new Address("City", "Street", 23, 32);
        profiles.add(new Profile(address));

        Address rsl = new ProfileStore().collect(profiles).get(0);

        assertThat(rsl, is(address));
    }
}