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

    @Test
    public void whenExtractAddressCollectionDistinct() {
        List<Profile> profiles = new ArrayList<>();

        profiles.add(new Profile(
                new Address("City2", "Street", 23, 32)
        ));
        profiles.add(new Profile(
                new Address("City2", "Street", 23, 32)
        ));
        profiles.add(new Profile(
                new Address("City1", "Street", 23, 32)
        ));

        List<Address> addresses = new ProfileStore().collectDistinct(profiles);

        assertThat(addresses.get(0), is(new Address("City1", "Street", 23, 32)));
        assertThat(addresses.size(), is(2));
    }
}