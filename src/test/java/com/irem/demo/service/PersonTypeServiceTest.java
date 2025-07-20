package com.irem.demo.service;

import com.irem.demo.dto.PersonTypeResponse;
import com.irem.demo.mapper.PersonTypeMapper;
import com.irem.demo.model.PersonType; // Model sınıfını import ediyoruz
import com.irem.demo.repository.PersonTypeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PersonTypeServiceTest {

    @Mock
    private PersonTypeRepository personTypeRepository; // Repository'i mock'luyoruz

    @Mock
    private PersonTypeMapper personTypeMapper; // Mapper'ı mock'luyoruz

    @InjectMocks
    private PersonTypeService personTypeService; // Test edilen servis, mock'lar buraya enjekte edilecek

    @Test
    void testGetAllPersonTypes() {
        // 1. Senaryo Hazırlığı (Given)
        // Mock PersonType nesneleri oluşturalım (repository'den dönecek olanlar)
        PersonType personType1 = new PersonType(); // PersonType sınıfınızda id ve name set edebileceğiniz kurucu veya setter'lar olmalı
        // Örnek: personType1.setId(1L); personType1.setName("Öğrenci");
        // Eğer PersonType sınıfında Lombok @AllArgsConstructor varsa:
        // PersonType personType1 = new PersonType(1L, "Öğrenci");
        // PersonType personType2 = new PersonType(2L, "Personel");

        // PersonType sınıfında kurucu olmadığı için varsayılan kurucuyu ve setter'ları kullanıyoruz.
        // Eğer PersonType sınıfınızda @AllArgsConstructor varsa, aşağıdaki gibi direkt kullanabilirsiniz.
        // Aksi takdirde, PersonType sınıfına id ve name alanlarını set edebileceğiniz bir constructor veya setter eklemelisiniz.
        // Varsayılan olarak boş bir PersonType objesi oluşturup id ve name'i set edelim:
        personType1.setId(1L);
        personType1.setName("Öğrenci");

        PersonType personType2 = new PersonType();
        personType2.setId(2L);
        personType2.setName("Personel");


        List<PersonType> mockPersonTypes = Arrays.asList(personType1, personType2);

        // Mock PersonTypeResponse nesneleri oluşturalım (mapper'dan dönecek olanlar)
        PersonTypeResponse response1 = new PersonTypeResponse(1L, "Öğrenci");
        PersonTypeResponse response2 = new PersonTypeResponse(2L, "Personel");
        List<PersonTypeResponse> expectedResponses = Arrays.asList(response1, response2);

        // Mock davranışlarını tanımla
        // personTypeRepository.findAll() çağrıldığında mockPersonTypes listesini döndür
        when(personTypeRepository.findAll()).thenReturn(mockPersonTypes);
        // personTypeMapper.toDtoList() çağrıldığında expectedResponses listesini döndür
        when(personTypeMapper.toDtoList(mockPersonTypes)).thenReturn(expectedResponses);

        // 2. Metodu Çağırma (When)
        List<PersonTypeResponse> actualResponses = personTypeService.getAllPersonTypes();

        // 3. Doğrulama (Then)
        // Sonucun null olmadığını kontrol et
        assertNotNull(actualResponses);
        // Listenin boyutunun beklenenle aynı olduğunu kontrol et
        assertEquals(2, actualResponses.size());
        // Listenin içeriğinin beklenenle aynı olduğunu kontrol et
        assertEquals(expectedResponses, actualResponses); // Listelerin eşitliğini doğrudan kontrol edebiliriz

        // Mock objelerin doğru şekilde çağrıldığını doğrula
        // repository.findAll() metodunun tam olarak bir kez çağrıldığını kontrol et
        verify(personTypeRepository, times(1)).findAll();
        // mapper.toDtoList() metodunun tam olarak bir kez, doğru argümanla çağrıldığını kontrol et
        verify(personTypeMapper, times(1)).toDtoList(mockPersonTypes);
    }

    @Test
    void testGetAllPersonTypes_noPersonTypes() {
        // Senaryo: Veritabanında hiç PersonType olmaması durumu
        // Mock PersonType listesi boş dönecek
        List<PersonType> emptyPersonTypes = Arrays.asList();
        List<PersonTypeResponse> emptyResponses = Arrays.asList();

        when(personTypeRepository.findAll()).thenReturn(emptyPersonTypes);
        when(personTypeMapper.toDtoList(emptyPersonTypes)).thenReturn(emptyResponses);

        List<PersonTypeResponse> actualResponses = personTypeService.getAllPersonTypes();

        assertNotNull(actualResponses);
        assertEquals(0, actualResponses.size());
        verify(personTypeRepository, times(1)).findAll();
        verify(personTypeMapper, times(1)).toDtoList(emptyPersonTypes);
    }
}