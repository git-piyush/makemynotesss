package com.piyush.InventoryManagementSystem.config;


import com.piyush.InventoryManagementSystem.dto.Match;
import com.piyush.InventoryManagementSystem.dto.MatchSubscriptionDTO;
import com.piyush.InventoryManagementSystem.entity.MatchSubscription;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper(){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE)
                .setMatchingStrategy(MatchingStrategies.STANDARD);

        modelMapper.addMappings(new PropertyMap<MatchSubscription, Match>() {
            @Override
            protected void configure() {
                map().setId(source.getMatchid());
                map().setTeams(source.getTeams());
                map().setDate(source.getDate());
                map().setTime(source.getTime());
            }
        });




        return modelMapper;

    }
}
