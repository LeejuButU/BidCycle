package LeejuButU.BidCycle.domain.photo.repository;

import LeejuButU.BidCycle.domain.photo.domain.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

public abstract class PhotoRepository implements JpaRepository<Photo, Long> {
}
