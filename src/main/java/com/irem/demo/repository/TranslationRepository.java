package com.irem.demo.repository;

import com.irem.demo.dto.TranslatedDTO;
import com.irem.demo.model.Translation;
import com.irem.demo.model.TranslationId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TranslationRepository extends JpaRepository<Translation, TranslationId> {

    /**
     * Belirli bir tablo ve dile göre çevirileri getir.
     */
    @Query(value = "SELECT record_id AS id, translation_text AS name " +
                   "FROM translations " +
                   "WHERE table_name = :tableName AND lang_code = :langCode",
           nativeQuery = true)
    List<TranslatedDTO> findTranslationsForTable(
            @Param("tableName") String tableName,
            @Param("langCode") String langCode
    );
}
