package org.onlinebookstore.onlinebookstorebackend.serviceimpl;
import org.onlinebookstore.onlinebookstorebackend.service.TimerService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("session")
public class TimerServiceImpl implements TimerService {
    private long startTime;
    private long endTime;

    @Override
    public void startTimer() {
        startTime = System.currentTimeMillis();
    }

    @Override
    public long stopTimer() {
        endTime = System.currentTimeMillis();
        return endTime - startTime;
    }
}
