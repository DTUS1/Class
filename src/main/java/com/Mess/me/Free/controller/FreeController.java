package com.Mess.me.Free.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

@RestController
public class FreeController {
	
	private static GpioPinDigitalOutput pinEye;
	private static GpioPinDigitalOutput pinMouth;
	private static GpioPinDigitalOutput pinHead;
	String state = String.format("Eyes: %s     Mouth: %s     Head: %s", (getEye().getState()).toString(),(getMouth().getState()).toString(), (getHead().getState()).toString() );

	
	@RequestMapping("/")
	public String init() {
		
		return state;
	}
	
	
	@RequestMapping("/eyes")
	public String eyes() {
		getEye().setState(PinState.HIGH);
		return state;
	}
	
	@RequestMapping("/mouth")
	public String mouth() {
		getMouth().setState(PinState.HIGH);
		return state;
	}
	
	@RequestMapping("/head")
	public String head() {
		getHead().setState(PinState.HIGH);
		return state;
	}
	
	@RequestMapping("/blink")
	public String blink() {
		getEye().blink(50L, 5000L);
		getMouth().blink(50L, 5000L);
		getHead().blink(50L, 5000L);
		return state;
	}
	
	@RequestMapping("/set1")
	public String set1() {
		getEye().pulse(1000L);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		getMouth().pulse(1000L);
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		getHead().pulse(1000L);
		
		return "Set1 Started";
	}
	
	@RequestMapping("/allOn")
	public String all() {
		getEye().setState(PinState.HIGH);
		getMouth().setState(PinState.HIGH);
		getHead().setState(PinState.HIGH);
		return state;
	}
	
	@RequestMapping("/allOff")
	public String allOff() {
		getEye().setState(PinState.LOW);
		getMouth().setState(PinState.LOW);
		getHead().setState(PinState.LOW);
		return state; 
	}
	
	//Inits + calls
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
