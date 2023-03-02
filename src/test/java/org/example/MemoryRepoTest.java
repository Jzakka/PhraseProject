package org.example;

import org.example.domain.Phrase;
import org.example.repository.MemoryRepo;
import org.example.repository.Repository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.*;

class MemoryRepoTest {
    Repository repo = MemoryRepo.getPhraseRepo();

    @BeforeEach
    void init() {
        repo.init();
    }

    @Test
    void register() throws IOException {
        repo.register("aaa", "aaa");
        repo.register("aaa", "aaa");

        assertThat(MemoryRepo.getNextId()).isEqualTo(3);
    }

    @Test
    void list() throws IOException {
        repo.register("aaa", "aaa");
        repo.register("bbb", "bbb");

        assertThat(repo.list().get(1l)).isEqualTo(new Phrase(1, "aaa", "aaa"));
        assertThat(repo.list().get(2l)).isEqualTo(new Phrase(2, "bbb", "bbb"));
    }

    @Test
    void delete() throws IOException {
        repo.register("aaa", "aaa");
        repo.register("bbb", "bbb");
        repo.register("ccc", "ccc");

        assertThat(repo.list().get(1l)).isEqualTo(new Phrase(1, "aaa", "aaa"));
        assertThat(repo.list().get(3l)).isEqualTo(new Phrase(3, "ccc", "ccc"));
    }

    @Test
    void deleteFail() throws IOException {
        assertThat(repo.delete(1l)).isFalse();
        assertThat(repo.delete(2l)).isFalse();
    }

    @Test
    void update() throws IOException {
        repo.register("aaa", "aaa");
        repo.register("bbb", "bbb");
        repo.register("ccc", "ccc");

        assertThat(repo.list().get(1l)).isEqualTo(new Phrase(1, "Updated Content", "mola"));
    }
}