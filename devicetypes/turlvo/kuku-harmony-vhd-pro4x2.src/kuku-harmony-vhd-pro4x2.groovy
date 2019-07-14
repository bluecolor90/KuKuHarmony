/**
 *  Copyright 2015 SmartThings
 *
 *  Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License. You may obtain a copy of the License at:
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software distributed under the License is distributed
 *  on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License
 *  for the specific language governing permissions and limitations under the License.
 *
 *  Momentary Button Tile
 *
 *  Author: jeognwoo kim
 *
 *  Date: 2013-05-01
 */
metadata {
	definition (name: "KuKu Harmony_VHD-PRO4X2", namespace: "turlvo", author: "jeongwoo kim") {
        capability "Actuator"
		capability "Switch"
		capability "Refresh"
		capability "Sensor"
        capability "Configuration"
        capability "Health Check"
        
        command "power"
		command "Audio_2ch"
		command "Audio_5P1CH"
		command "Audio_ADV"
		command "Audio_ARC"
		command "Audio_OnOFF"
		command "OutputA_1"
		command "OutputA_2"
		command "OutputA_3"
		command "OutputA_4"
		command "OutputB_1"
		command "OutputB_2"
		command "OutputB_3"
		command "OutputB_4"
		command "virtualpower"
	}

	// simulator metadata
	simulator {
	}
    
    preferences {
        input name: "momentaryOn", type: "bool",title: "Enable Momentary on (for garage door controller)", required: false
        input name: "momentaryOnDelay", type: "num",title: "Enable Momentary on dealy time(default 5 seconds)", required: false
    }

	// UI tile definitions
	tiles (scale: 2){      
		multiAttributeTile(name:"switch", type: "generic", width: 6, height: 4, canChangeIcon: true){
			tileAttribute ("device.switch", key: "PRIMARY_CONTROL") {
                attributeState "off", label:'${name}', action:"switch.on", backgroundColor:"#ffffff", icon: "st.switches.switch.off", nextState:"turningOn"
				attributeState "on", label:'${name}', action:"switch.off", backgroundColor:"#79b821", icon: "st.switches.switch.on", nextState:"turningOff"				
				attributeState "turningOn", label:'${name}', action:"switch.off", backgroundColor:"#79b821", icon: "st.switches.switch.off", nextState:"turningOff"
				attributeState "turningOff", label:'${name}', action:"switch.on", backgroundColor:"#ffffff", icon: "st.switches.switch.on", nextState:"turningOn"
			}
        }
        standardTile("power", "device.switch", width: 6, height: 3) {
    		state "off", label:'${name}', action:"switch.on", backgroundColor:"#ffffff", icon: "st.switches.switch.off", nextState:"turningOn"
			state "on", label:'${name}', action:"switch.off", backgroundColor:"#00a0dc", icon: "st.switches.switch.on", nextState:"turningOff"				
			state "turningOn", label:'${name}', action:"switch.off", backgroundColor:"#00a0dc", icon: "st.switches.switch.off", nextState:"turningOff"
			state "turningOff", label:'${name}', action:"switch.on", backgroundColor:"#ffffff", icon: "st.switches.switch.on", nextState:"turningOn"
		}

        valueTile("OutputA_1", "device.OutputA_1",width: 2, height: 1, decoration: "flat", canChangeIcon: false) {
            state "yes", label: "OutA_In1", backgroundColor: "#ffffff", action: "OutputA_1"
        }
        valueTile("Audio_2ch", "device.Audio_2ch", width: 2, height: 1, decoration: "flat", canChangeIcon: false) {
            state "yes", label: "2ch", backgroundColor: "#ffffff", action: "Audio_2ch"
        }
        valueTile("B_1", "device.OutputB_1", width: 2, height: 1, decoration: "flat", canChangeIcon: false) {
            state "yes", label: "OutB_In1", backgroundColor: "#ffffff", action: "OutputB_1"
        }
        valueTile("A_2", "device.OutputA_2", width: 2, height: 1, decoration: "flat", canChangeIcon: false) {
            state "on", label: "OutA_In2", backgroundColor: "#ffffff", action: "OutputA_2"
        }
        valueTile("5P1CH", "device.Audio_5P1CH", width: 2, height: 1, decoration: "flat", canChangeIcon: false) {
            state "yes", label: "5P1CH", backgroundColor: "#ffffff", action: "Audio_5P1CH"
        }
        valueTile("B_2", "device.OutputB_2", width: 2, height: 1, decoration: "flat", canChangeIcon: false) {
            state "on", label: "OutB_In2", backgroundColor: "#ffffff", action: "OutputB_2"
            state "off", label: "-", backgroundColor: "#00a0dc",action: ""
        }
        valueTile("A_3", "device.OutputA_3", width: 2, height: 1, decoration: "flat", canChangeIcon: false) {
            state "on", label: "OutA_In3", backgroundColor: "#ffffff", action: "OutputA_3"
            state "off", label: "-", backgroundColor: "#00a0dc",action: ""
        }
        valueTile("ADV", "device.Audio_ADV", width: 2, height: 1, decoration: "flat", canChangeIcon: false) {
            state "on", label: "ADV", backgroundColor: "#ffffff", action: "Audio_ADV"
            state "off", label: "-", backgroundColor: "#00a0dc",action: ""
        }
        valueTile("B_3", "device.OutputB_3", width: 2, height: 1, decoration: "flat", canChangeIcon: false) {
            state "on", label: "OutB_In3", backgroundColor: "#ffffff", action: "OutputB_3"
            state "off", label: "-", backgroundColor: "#00a0dc",action: ""
        }
        valueTile("A_4", "device.OutputA_4", width: 2, height: 1, decoration: "flat", canChangeIcon: false) {
            state "on", label: "OutA_In4", backgroundColor: "#ffffff", action: "OutputA_4"
            state "off", label: "-", backgroundColor: "#00a0dc",action: ""
        }
        valueTile("ARC", "device.Audio_ARC", width: 2, height: 1, decoration: "flat", canChangeIcon: false) {
            state "on", label: "ARC", backgroundColor: "#ffffff", action: "Audio_ARC"
            state "off", label: "-", backgroundColor: "#00a0dc",action: ""
        }
        valueTile("B_4", "device.OutputB_4", width: 2, height: 1, decoration: "flat", canChangeIcon: false) {
            state "on", label: "OutB_In4", backgroundColor: "#ffffff", action: "OutputB_4"
            state "off", label: "-", backgroundColor: "#00a0dc",action: ""
        }
        valueTile("Audio_OnOFF", "device.Audio_OnOFF", width: 2, height: 1, decoration: "flat", canChangeIcon: false) {
        	state "on", label: "Audio_OnOFF", backgroundColor: "#ffffff", action: "Audio_OnOFF"
            state "off", label: "-", backgroundColor: "#00a0dc",action: ""
        }
		valueTile("blank", "device.switch", width: 2, height: 1, decoration: "flat", canChangeIcon: false) {
        	state "on", label: "", backgroundColor: "#ffffff", action: ""
            state "off", label: "", backgroundColor: "#ffffff",action: ""
        }
		valueTile("virtualpower", "device.switch", width: 2, height: 1, decoration: "flat", canChangeIcon: false, canChangeBackground: false) {
            state "on", label: "virtualOff", action: "virtualpower"
			state "off", label: "virtualOn", action: "virtualpower"
        }
		
		
        
	}
    main(["switch"])
	details(["power", 
    		"OutputA_1", "Audio_2ch", "B_1",
            "A_2", "5P1CH", "B_2",
            "A_3", "ADV", "B_3",
            "A_4", "ARC", "B_4",
            "virtualpower","Audio_OnOFF","blank"
            ])
}

def parse(String description) {
	log.debug "Parsing '${description}'"
}
def on() {
	parent.command(this, "power-on")
    sendEvent(name: "switch", value: "on")
	
	if (momentaryOn) {
    	if (settings.momentaryOnDelay == null || settings.momentaryOnDelay == "" ) settings.momentaryOnDelay = 5
    	log.debug "momentaryOnHandler() >> time : " + settings.momentaryOnDelay
    	runIn(Integer.parseInt(settings.momentaryOnDelay), momentaryOnHandler, [overwrite: true])
    }
	log.debug "Switch.on>>${device.currentState("switch")?.value}"
}
def off() {
	parent.command(this, "power-off")
    sendEvent(name: "switch", value: "off")
	log.debug "Switch.off_CurrentStatus>>${device.currentState("switch")?.value}"
}
def virtualpower() {	
    if(device.currentValue("switch")=="on")
	{
		log.debug "child virtualoff()"
		sendEvent(name:"switch",value:"off")
	}
	else
	{
		log.debug "child virtualoff()"
		sendEvent(name:"switch",value:"on")
	}
}
def OutputA_1(){
	log.debug "VHDPRO4X2> OutputA_1()"
    parent.command(this, "OutputA_1")
}
def OutputA_2(){
	log.debug "VHDPRO4X2> A_2()"
    parent.command(this, "OutputA_2")
}
def OutputA_3(){
	log.debug "VHDPRO4X2> A_3()"
    parent.command(this, "OutputA_3")
}
def OutputA_4(){
	log.debug "VHDPRO4X2> A_4()"
    parent.command(this, "OutputA_4")
}
def OutputB_1(){
	log.debug "VHDPRO4X2> B_1()"
    parent.command(this, "OutputB_1")
}
def OutputB_2(){
	log.debug "VHDPRO4X2> B_2()"
    parent.command(this, "OutputB_2")
}
def OutputB_3(){
	log.debug "VHDPRO4X2> B_3()"
    parent.command(this, "OutputB_3")
}
def OutputB_4(){
	log.debug "VHDPRO4X2> B_4()"
    parent.command(this, "OutputB_4")
}
def Audio_2ch(){
    log.debug "VHDPRO4X2> Audio_2ch"
    parent.command(this, "Audio_2ch")
}
def Audio_ADV(){
    log.debug "VHDPRO4X2> Audio_ADV"
    parent.command(this, "Audio_ADV")
}
def Audio_ARC(){
    log.debug "VHDPRO4X2> Audio_ARC"
    parent.command(this, "Audio_ARC")
}
def Audio_OnOFF(){
    log.debug "VHDPRO4X2> Audio_OnOFF"
    parent.command(this, "Audio_OnOFF")
}
def generateEvent(Map results) {
    results.each { name, value ->
		log.debug "generateEvent>> name: $name, value: $value"
        def currentState = device.currentValue("switch")
		log.debug "generateEvent>> currentState: $currentState"
        if (currentState != value) {
        	log.debug "generateEvent>> changed to $value"
        	sendEvent(name: "switch", value: value)
        } else {
        	log.debug "generateEvent>> not change"
        }
    }
    return null
}

def poll() {
	log.debug "poll()"
}

def parseEventData(Map results) {
    results.each { name, value ->
        //Parse events and optionally create SmartThings events
    }
}
def virtualOn() {
	log.debug "VHDPRO4X2> on()"	
    sendEvent(name: "switch", value: "on")
}

def virtualOff() {
	log.debug "VHDPRO4X2> off"	
    sendEvent(name: "switch", value: "off")
}
def momentaryOnHandler() {
	log.debug "momentaryOnHandler()"
	sendEvent(name: "switch", value: "off")
}