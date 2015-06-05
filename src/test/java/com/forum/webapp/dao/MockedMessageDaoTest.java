package com.forum.webapp.dao;

import java.util.LinkedList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import com.forum.webapp.dao.impl.MessageDao;
import com.forum.webapp.entities.MessageEntity;
import com.forum.webapp.entities.MessageFilter;
import com.forum.webapp.repos.MessageRepository;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MockedMessageDaoTest {

    @Spy
    MessageDao daoToTest = new MessageDao();
    
    @Mock
    MessageRepository messageRepository;
    
    @Before
    public void setUp(){
        daoToTest.setMessageRepository(messageRepository);
    }
    
    @After
    public void tearDown(){
        reset(messageRepository);
    }
    
    @Test
    @Ignore
    public void filter_by_matching_subject(){
        List<MessageEntity> messages = new LinkedList<>();
        MessageEntity message = new MessageEntity();
        messages.add(message);
        message = new MessageEntity();
        messages.add(message);
        
        when(messageRepository.findByTextLikeIgnoreCase(anyString())).thenReturn(null);
        when(messageRepository.findByTextLikeIgnoreCase("SEARCH")).thenReturn(messages);
        
        MessageFilter filter = new MessageFilter();
        filter.setContentToContain("SEARCH");
        
        // TODO call the method on the DAO and check the result.
        
        verify(messageRepository, times(1)).findByTextLikeIgnoreCase(eq("SEARCH"));        
    }

    @Test
    @Ignore
    public void filter_by_not_matching_subject(){
        List<MessageEntity> messages = new LinkedList<>();
        MessageEntity message = new MessageEntity();
        messages.add(message);
        message = new MessageEntity();
        messages.add(message);
        
        when(messageRepository.findByTextLikeIgnoreCase(anyString())).thenReturn(null);
        when(messageRepository.findByTextLikeIgnoreCase("SEARCH")).thenReturn(messages);
        
        MessageFilter filter = new MessageFilter();
        filter.setContentToContain("NOT_SEARCH");
        
        // TODO call the method on the DAO and check the result.
        
        verify(messageRepository, times(1)).findByTextLikeIgnoreCase(eq("NOT_SEARCH"));        
    }
}
