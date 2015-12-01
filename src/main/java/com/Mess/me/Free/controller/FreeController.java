package com.Mess.me.Free.controller;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

@SpringBootApplication
public class FreeController {
	
	private static GpioPinDigitalOutput pinEye;
	private static GpioPinDigitalOutput pinMouth;
	private static GpioPinDigitalOutput pinHead;
	
	
	@RequestMapping("/")
	public String init() {
		return "Running";
	}
	
	
	@RequestMapping("/eyes")
	public String eyes() {
		getEye().setState(PinState.HIGH);
		return (getEye().getState()).toString();
	}
	
	@RequestMapping("/mouth")
	public String mouth() {
		getMouth().setState(PinState.HIGH);
		return (getMouth().getState()).toString();
	}
	
	@RequestMapping("/head")
	public String head() {
		getHead().setState(PinState.HIGH);
		return (getHead().getState()).toString();
	}
	
	@RequestMapping("/allOn")
	public String all() {
		getEye().setState(PinState.HIGH);
		getMouth().setState(PinState.HIGH);
		getHead().setState(PinState.HIGH);
		return "All On";
	}
	
	@RequestMapping("/allOff")
	public String allOff() {
		getEye().setState(PinState.LOW);
		getMouth().setState(PinState.LOW);
		getHead().setState(PinState.LOW);
		return "All Off";
	}
	
	
	public GpioPinDigitalOutput getEye() {
		
		if(pinEye == null) {
			
			GpioController gpio = GpioFactory.getInstance();
			pinEye = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_00, "Led1", PinState.LOW);
			
			}
		return pinEye;
	}
	public GpioPinDigitalOutput getHead() {
		
		if(pinHead == null) {
			
			GpioController gpio = GpioFactory.getInstance();
			pinHead = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01, "Led2", PinState.LOW);
			
			}
		return pinHead;
	}
public GpioPinDigitalOutput getMouth() {
		
		if(pinMouth == null) {
			
			GpioController gpio = GpioFactory.getInstance();
			pinMouth = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_02, "Led3", PinState.LOW);
			
			}
		return pinMouth;
	}
	
}
