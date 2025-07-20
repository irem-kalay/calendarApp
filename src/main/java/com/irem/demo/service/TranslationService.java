package com.irem.demo.service;

import com.irem.demo.dto.TranslatedDTO;
import com.irem.demo.repository.TranslationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TranslationService {

    private final TranslationRepository translationRepository;

    public TranslationService(TranslationRepository translationRepository) {
        this.translationRepository = translationRepository;
    }

    /**
     * Verilen tabloya ve dile ait çevirileri bir Map olarak döndürür.
     * Key: ID, Value: Çevrilmiş isim
     */
    public Map<Long, String> getTranslationsAsMap(String tableName, String langCode) {
        List<TranslatedDTO> translations = translationRepository.findTranslationsForTable(tableName, langCode);
        return translations.stream()
                .collect(Collectors.toMap(TranslatedDTO::getId, TranslatedDTO::getName));
    }
}
