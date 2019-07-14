/**
 *  KuKu Harmony_AviairR10
 *
 *  Copyright 2018 jeongwoo kim
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
 */
metadata {
	definition (name: "KuKu Harmony_AviairR10", namespace: "turlvo", author: "jeongwoo kim") {
		capability "Actuator"
		capability "Configuration"
		capability "Refresh"
		capability "Sensor"
		capability "Switch"

		command "power"
		command "HorizonSwing"
		command "VerticalSwing"
		command "FanUp"
		command "FanDown"
		command "Timer"
		command "FanMode"
	}


	simulator {
		// TODO: define status and reply messages here
	}
    preferences {
        input name: "momentaryOn", type: "bool",title: "Enable Momentary on (for garage door controller)", required: false
        input name: "momentaryOnDelay", type: "num",title: "Enable Momentary on dealy time(default 5 seconds)", required: false
    }

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
		valueTile("FanDown", "device.FanDown",width: 3, height: 2, decoration: "flat", canChangeIcon: false) {
            state "yes", label: "FanDown", backgroundColor: "#ffffff", action: "FanDown"
        }
		valueTile("FanUp", "device.FanUp",width: 3, height: 2, decoration: "flat", canChangeIcon: false) {
            state "yes", label: "FanUp", backgroundColor: "#ffffff", action: "FanUp"
        }
		valueTile("VerticalSwing", "device.VerticalSwing",width: 3, height: 2, decoration: "flat", canChangeIcon: false) {
            state "yes", label: "VerticalSwing", backgroundColor: "#ffffff", action: "VerticalSwing"
        }
		valueTile("HorizonSwing", "device.HorizonSwing",width: 3, height: 2, decoration: "flat", canChangeIcon: false) {
            state "yes", label: "HorizonSwing", backgroundColor: "#ffffff", action: "HorizonSwing"
        }
		valueTile("FanMode", "device.FanMode",width: 3, height: 2, decoration: "flat", canChangeIcon: false) {
            state "yes", label: "FanMode", backgroundColor: "#ffffff", action: "FanMode"
        }
		valueTile("Timer", "device.Timer",width: 3, height: 2, decoration: "flat", canChangeIcon: false) {
            state "yes", label: "Timer", backgroundColor: "#ffffff", action: "Timer"
        }
	}
	main(["switch"])
	details(["power", 
    		"FanUp", "FanDown", 
			"VerticalSwing", "HorizonSwing",
            "FanMode", "Timer"
            ])
}

// parse events into attributes
def parse(String description) {
	log.debug "Parsing '${description}'"
	// TODO: handle 'switch' attribute

}

// handle commands
def configure() {
	log.debug "Executing 'configure'"
	// TODO: handle 'configure' command
}

def refresh() {
	log.debug "Executing 'refresh'"
	// TODO: handle 'refresh' command
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

def power() {
	log.debug "Executing 'power'"
	// TODO: handle 'power' command
}

def HorizonSwing() {
	log.debug "Executing 'HorizonSwing'"
	parent.command(this,"HorizonSwing")
}

def VerticalSwing() {
	log.debug "Executing 'VerticalSwing'"
	parent.command(this,"VerticalSwing")
}

def FanUp() {
	log.debug "Executing 'FanUp'"
	parent.command(this,"FanUp")
}

def FanDown() {
	log.debug "Executing 'FanDown'"
	parent.command(this,"FanDown")
}

def Timer() {
	log.debug "Executing 'Timer'"
	parent.command(this,"Timer")
}

def FanMode() {
	log.debug "Executing 'FanMode'"
	parent.command(this,"FanMode")
}