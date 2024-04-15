package LeejuButU.BidCycle.domain.photo.repository;

import LeejuButU.BidCycle.domain.photo.domain.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoRepository extends JpaRepository<Photo, Long> {
}
