package guru.springframework.spring6resttemplate.client;

import guru.springframework.spring6resttemplate.model.BeerDTO;
import guru.springframework.spring6resttemplate.model.BeerStyle;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class BeerClientImplTest {

    @Autowired
    BeerClientImpl beerClient;

    @Test
    void listBeersNoBeerName() {
        Page<BeerDTO> result = beerClient.listBeers(null, null, null, null, null);
        assertThat(result.getContent()).isNotNull();
    }

    @Test
    void listBeersByName() {
        Page<BeerDTO> result = beerClient.listBeers("ALE", null, null, null, null);
        assertThat(result.getContent()).isNotNull();
    }

    @Test
    void listBeersByStyle() {
        Page<BeerDTO> result = beerClient.listBeers(null, BeerStyle.ALE, null, null, null);
        assertThat(result.getContent()).isNotNull();
    }

    @Test
    void listBeersWithDifferentPageSizes() {
        for (Integer pageSize = 5; pageSize <= 25; pageSize+=5) {
            Page<BeerDTO> result = beerClient.listBeers(null, null, null, null, pageSize);
            System.out.println("Testing API with page size of " + pageSize.toString());
            assertEquals(pageSize, result.getContent().size());
        }
        System.out.println("Paging works! :)");
    }

}