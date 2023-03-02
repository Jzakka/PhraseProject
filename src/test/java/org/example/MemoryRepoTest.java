package org.example;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemoryRepoTest {
    Repository repo = MemoryRepo.getPhraseRepo();

    @BeforeEach
    void init() {
        repo.init();
    }

    @Test
    void register() {
        repo.register("aaa", "aaa");
        repo.register("aaa", "aaa");

        assertThat(MemoryRepo.getNextId()).isEqualTo(3);
    }

    @Test
    void list() {
        repo.register("aaa", "aaa");
        repo.register("bbb", "bbb");

        assertThat(repo.list().get(1l)).isEqualTo(new Phrase(1, "aaa", "aaa"));
        assertThat(repo.list().get(2l)).isEqualTo(new Phrase(2, "bbb", "bbb"));
    }

    @Test
    void delete() {
        repo.register("aaa", "aaa");
        repo.register("bbb", "bbb");
        repo.register("ccc", "ccc");
        assertThat(repo.delete(2l)).isTrue();

        assertThat(repo.list().get(1l)).isEqualTo(new Phrase(1, "aaa", "aaa"));
        assertThat(repo.list().get(3l)).isEqualTo(new Phrase(3, "ccc", "ccc"));
    }

    @Test
    void deleteFail(){
        assertThat(repo.delete(1l)).isFalse();
        assertThat(repo.delete(2l)).isFalse();
    }

    @Test
    void update(){
        repo.register("aaa", "aaa");
        repo.register("bbb", "bbb");
        repo.register("ccc", "ccc");

        assertThat(repo.update(1, "Updated Content", "mola")).isTrue();
        assertThat(repo.list().get(1l)).isEqualTo(new Phrase(1, "Updated Content", "mola"));
    }
}