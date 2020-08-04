package stream;

import java.util.List;
import java.util.stream.Collectors;

public class ProfileStore {
    public List<Address> collect(List<Profile> profiles) {
        return profiles.stream().map(
                Profile::getAddress
        ).collect(Collectors.toList());
    }
}
