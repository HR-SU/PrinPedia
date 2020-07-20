package com.prinpedia.backend.dataProcess;

import com.prinpedia.backend.repository.EntryRelationRepository;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

@Ignore
@SpringBootTest
public class InitEntityRelation {
    @Autowired
    private EntryRelationRepository entryRelationRepository;

    @Ignore
    @Test
    public void entryRelation() throws IOException {
        String path = "G:\\webWorkspace\\prinpedia\\entryLinkTest.txt";

        File file = new File(path);
        String tmp;
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        while((tmp = bufferedReader.readLine()) != null) {
            String []strings = (tmp.split("\\s+"));
            int parent = Integer.parseInt(strings[0]);
            int child = Integer.parseInt(strings[1]);
            entryRelationRepository.createEntryRelation(parent, child);
        }

    }
}
