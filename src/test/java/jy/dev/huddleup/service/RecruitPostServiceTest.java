package jy.dev.huddleup.service;

import jy.dev.huddleup.repository.RecruitPostRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class RecruitPostServiceTest {

    @InjectMocks
    RecruitPostService recruitPostService;

    @Mock
    RecruitPostRepository recruitPostRepository;
}