package com.example.sample.common.service;

import com.example.sample.dto.PopularResultDto;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.validation.constraints.NotNull;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.Collectors;

@Component
public class ConcurrentService {

    private ConcurrentHashMap<String, LongAdder> concurrentHashMap;

    @PostConstruct
    public void initConcurrentMap() {
        concurrentHashMap = new ConcurrentHashMap<>();
    }

    private final LongAdder counter = new LongAdder();

    public void increment(@NotNull String query) {
        if (concurrentHashMap.containsKey(query)) {
            LongAdder adder = concurrentHashMap.get(query);
            adder.increment();
            concurrentHashMap.put(query, adder);
        } else {
            LongAdder newAdder = new LongAdder();
            newAdder.increment();
            concurrentHashMap.put(query, newAdder);
        }
    }

    public LinkedHashMap<String, Long> getPopularList() {

        int popularSize = 10;

        LinkedHashMap<String, LongAdder> sortedMap = sortByValue(concurrentHashMap);

        LinkedHashMap<String, LongAdder> reversedMap = reverseMap(sortedMap);

        LinkedHashMap<String, LongAdder> splitMap = reversedMap.entrySet().stream()
                .limit(popularSize)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

        return convertToLongMap(splitMap);
    }


    public long getLongValue(@NotNull String query) {
        if (!concurrentHashMap.containsKey(query)) {
            return 0L;
        }
        return concurrentHashMap.get(query).longValue();
    }

    public int getIntValue(@NotNull String query) {
        if (!concurrentHashMap.containsKey(query)) {
            return 0;
        }
        return concurrentHashMap.get(query).intValue();
    }



    private LinkedHashMap<String, LongAdder> sortByValue(ConcurrentHashMap<String, LongAdder> map) {
        List<Map.Entry<String, LongAdder>> sortedEntries = new ArrayList<>(map.entrySet());
        sortedEntries.sort(Comparator.comparingLong(e -> e.getValue().longValue()));

        return sortedEntries.stream()
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }

    private <String, LongAdder> LinkedHashMap<String, LongAdder> reverseMap(LinkedHashMap<String, LongAdder> map) {
        List<Map.Entry<String, LongAdder>> entries = new ArrayList<>(map.entrySet());
        Collections.reverse(entries);

        return entries.stream()
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }

    private LinkedHashMap<String, Long> convertToLongMap(LinkedHashMap<String, LongAdder> map) {
        LinkedHashMap<String, Long> longMap = new LinkedHashMap<>();

        for (Map.Entry<String, LongAdder> entry : map.entrySet()) {
            String key = entry.getKey();
            LongAdder value = entry.getValue();
            longMap.put(key, value.longValue());
        }

        return longMap;
    }




}
