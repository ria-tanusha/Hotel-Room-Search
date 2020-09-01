package com.hotel.room.search.exceptionhandling;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ExceptionHandlerControllerAdviceTest {

	@InjectMocks
	private ExceptionHandlerControllerAdvice exceptionHandlerControllerAdvice;

	@Test
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void handleRoomTypeTest() {
		RoomTypeNotFoundException ex = new RoomTypeNotFoundException("No room type found");
		exceptionHandlerControllerAdvice.handleRoomType(ex);
	}
}
