package persistence;

import domain.Advertisement;

import java.util.List;

public interface AdvertisementDao {
    public List<Advertisement> allAdvertisement();
    public boolean updateAdvertisement(Advertisement advertisement, int productid);
    public boolean deleteAdvertisement(int advertisementId);
    public boolean saveAdvertisement(Advertisement advertisement, int productid);
}
