package ru.skypro.homework.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.mock.web.MockPart;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.repository.AdEntityRepository;
import ru.skypro.homework.service.AdService;

import java.security.Principal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AdsController.class)
public class AdsControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private AdEntityRepository adEntityRepository;
    @MockBean
    private AdService adService;

    private final ObjectMapper objectMapper = new ObjectMapper();



    @Test
    @WithMockUser
    void testGetAds() throws Exception {
        Ads fakeAds = new Ads();

        when(adService.getAllAds()).thenReturn(fakeAds);

        mockMvc.perform(get("/ads"))
                .andExpect(status().isOk());
        verify(adService,times(1)).getAllAds();
    }
    @Test
    @WithMockUser
    void testAddAds() throws Exception {
        MockMultipartFile filePart = new MockMultipartFile(
                "image",
                "image.png",
                "image/png",
                "Test Image".getBytes());

        Ad ad = new Ad();
        ad.setPk(1);
        ad.setTitle("Кроссовки");
        ad.setPrice(5000);
        ad.setAuthor(1);
        ad.setImage(filePart.toString());

        CreateOrUpdateAd createOrUpdateAd = new CreateOrUpdateAd(ad.getTitle(), ad.getPrice(), "Крутые-кроссы");
        MockPart jsonPart = new MockPart("properties", null, objectMapper.writeValueAsBytes(createOrUpdateAd));
        jsonPart.getHeaders().setContentType(MediaType.APPLICATION_JSON);

        when(adService.saveAd(any(CreateOrUpdateAd.class), any(MultipartFile.class), any(Principal.class)))
                .thenReturn(ad);

        mockMvc.perform(MockMvcRequestBuilders
                .multipart("/ads")
                .part(jsonPart)
                .file(filePart)
                .with(csrf())
                ).andExpect(status().isCreated());
        verify(adService,times(1))
                .saveAd(any(CreateOrUpdateAd.class), any(MultipartFile.class), any(Principal.class));
    }
    @Test
    @WithMockUser
    void testGetInformationAboutAd() throws Exception{
        Ad ad = new Ad();
        ad.setPk(1);
        ad.setTitle("Кроссовки");
        ad.setPrice(5000);
        ad.setAuthor(1);

        ExtendedAd extendedAd = new ExtendedAd();
        extendedAd.setPk(ad.getPk());
        extendedAd.setTitle(ad.getTitle());
        extendedAd.setPrice(ad.getPrice());

        when(adService.getInformationAboutAd(any(Integer.class))).thenReturn(extendedAd);

        mockMvc.perform(get("/ads/{id}", ad.getPk()))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.pk").value(extendedAd.getPk()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value(extendedAd.getTitle()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(extendedAd.getPrice()));

        verify(adService,times(1)).getInformationAboutAd(any(Integer.class));
    }
    @Test
    @WithMockUser
    void testDeleteAd() throws Exception{
        Ad ad = new Ad();
        ad.setPk(1);
        ad.setTitle("Кроссовки");
        ad.setPrice(5000);
        ad.setAuthor(1);
        mockMvc.perform(delete("/ads/{id}",ad.getPk())
                        .with(csrf()))
                        .andExpect(status().isNoContent());

        verify(adService,times(1)).deleteAd(any(Integer.class));
    }

}
