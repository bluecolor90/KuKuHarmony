/**
 *  KuKu Harmony - Virtual Switch for Logitech Harmony
 *
 *  Copyright 2017 KuKu <turlvo@gmail.com>
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

metadata {
	definition (name: "KuKu Harmony_Aircon", namespace: "turlvo", author: "KuKu") {
        capability "Actuator"
		capability "Switch"
		capability "Refresh"
		capability "Sensor"
        capability "Configuration"
        capability "Health Check"
        
        command "power"
        command "tempup"
        // command "mode"
        // command "jetcool"
        command "tempdown"
        // command "speed"
        command "setRangedLevel", ["number"]

		command "fanhigh"
		command "turbo"
		command "fanlow" 
		command "energysaver"
		command "dry"
		command "cool"
        // command "custom1"
        // command "custom2"
        // command "custom3"
        // command "custom4"
        // command "custom5"
		command "virtualpower"
        command "virtualOn"
        command "virtualOff"
		command "FanBeforeOff"
	}

    preferences {
        input name: "momentaryOn", type: "bool",title: "Enable Momentary on (for garage door controller)", required: false
        input name: "momentaryOnDelay", type: "num",title: "Enable Momentary on dealy time(default 5 seconds)", required: false
		input name: "momentaryOn", type: "bool",title: "Enable Momentary on (for garage door controller)", required: false
		input name: "numOnDelay", type: "num",title: "on dealy time(default 5 seconds)", required: false
		input name: "numOffDelay", type: "num",title: "off dealy time(default 5 seconds)", required: false
		input name: "FanModeBeforeOff", type: "bool",title: "Activate Fanmode for set seconds before turn off ", required: false
		input name: "numFanModeBeforeOff", type: "num",title: "Activate Fanmode for set seconds before turn off dealy time(default 300 seconds)", required: false
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

        valueTile("power", "device.power", width: 2, height: 2, decoration: "flat", canChangeIcon: false, canChangeBackground: false) {
            state "yes", label: "POWER", action: "power"
            state "no", label: "unavail", action: ""
        }
        valueTile("tempup", "device.tempup", width: 2, height: 2, decoration: "flat", canChangeIcon: false, canChangeBackground: false) {
            state "yes", label: "TEMP UP", action: "tempup"
            state "no", label: "unavail", action: ""
        }
		valueTile("fanhigh", "device.fanhigh", width: 2, height: 2, decoration: "flat", canChangeIcon: false, canChangeBackground: false) {
            state "yes", label: "FAN HIGH", action: "fanhigh"
            state "no", label: "unavail", action: ""
        }
        // valueTile("mode", "device.mode", width: 2, height: 2, decoration: "flat", canChangeIcon: false, canChangeBackground: false) {
            // state "yes", label: "MODE", action: "mode"
            // state "no", label: "unavail", action: ""
        // }
        
        // valueTile("jetcool", "device.jetcool", width: 2, height: 2, decoration: "flat", canChangeIcon: false, canChangeBackground: false) {
            // state "yes", label: "JET MODE", action: "jetcool"
            // state "no", label: "unavail", action: ""
        // }
		valueTile("turbo", "device.turbo", width: 2, height: 2, decoration: "flat", canChangeIcon: false, canChangeBackground: false) {
            state "yes", label: "TURBO MODE", action: "turbo"
            state "no", label: "unavail", action: ""
        }
        valueTile("tempdown", "device.tempdown", width: 2, height: 2, decoration: "flat", canChangeIcon: false, canChangeBackground: false) {
            state "yes", label: "TEMP DOWN", action: "tempdown"
            state "no", label: "unavail", action: ""
        }
		valueTile("fanlow", "device.fanlow", width: 2, height: 2, decoration: "flat", canChangeIcon: false, canChangeBackground: false) {
            state "yes", label: "FAN LOW", action: "fanlow"
            state "no", label: "unavail", action: ""
        }
        // valueTile("speed", "device.speed", width: 2, height: 2, decoration: "flat", canChangeIcon: false, canChangeBackground: false) {
            // state "yes", label: "FAN SPEED", action: "speed"
            // state "no", label: "unavail", action: ""
        // }
		valueTile("energysaver", "device.energysaver", width: 2, height: 2, decoration: "flat", canChangeIcon: false, canChangeBackground: false) {
            state "yes", label: "Energy Saving", action: "energysaver"
            state "no", label: "unavail", action: ""
        }
		valueTile("dry", "device.dry", width: 2, height: 2, decoration: "flat", canChangeIcon: false, canChangeBackground: false) {
            state "yes", label: "DRY", action: "dry"
            state "no", label: "unavail", action: ""
        }
		valueTile("cool", "device.cool", width: 2, height: 2, decoration: "flat", canChangeIcon: false, canChangeBackground: false) {
            state "yes", label: "COOL", action: "cool"
            state "no", label: "unavail", action: ""
        }
        controlTile("tempSliderControl", "device.level", "slider", range:"(18..30)", height: 2, width: 4) {
            state "level", action:"setRangedLevel"
        }
        valueTile("tempSliderControlValue", "device.level", height: 2, width: 2) {
			state "range", label:'Temperature\n${currentValue}°C', defaultState: true
		}
		valueTile("virtualpower", "device.switch", width: 2, height: 1, decoration: "flat", canChangeIcon: false, canChangeBackground: false) {
            state "on", label: "virtualOff", action: "virtualpower"
			state "off", label: "virtualOn", action: "virtualpower"
        }
		valueTile("FanBeforeOff", "device.switch", width: 2, height: 1, decoration: "flat", canChangeIcon: false, canChangeBackground: false) {
            state "yes", label: "FanBeforeOff", action: "FanBeforeOff"
            state "no", label: "unavail", action: ""
        }
    }

	main(["switch"])
	// details(["power", "tempup", "mode",
            // "jetcool", "tempdown", "speed", 
            // "tempSliderControl", "tempSliderControlValue"])
	details(["power", "tempup", "fanhigh",
            "turbo", "tempdown", "fanlow", 
			"energysaver","dry","cool",
            "tempSliderControl", "tempSliderControlValue",
			"virtualpower","FanBeforeOff"])			
}

def installed() {
	log.debug "installed()"
	//configure()
}

// parse events into attributes
def parse(String description) {
	log.debug "Parsing '${description}'"
}

def power() {
    log.debug "child power()"
    
    //def currentState = device.currentState("switch")?.value
    def currentState = device.currentValue("switch")
    log.debug "power>> state: $currentState"

    if (currentState == "on") {
		off()
    } else {
        on()
    }
}

def tempup() {
    log.debug "child tempup()"
    parent.command(this, "tempup")
}

def mode() {
    log.debug "child mode()"
    parent.command(this, "mode")
}

//def jetcool() {
//    log.debug "child jetcool()"
   // parent.command(this, "jetcool")
// }

def tempdown() {
    log.debug "child tempdown()"
    parent.command(this, "tempdown")
}

def speed() {
    log.debug "child speed()"
    parent.command(this, "speed")
    
}
def turbo() {
     log.debug "child turbo"
	 parent.command(this,"turbo") 
}
def fanlow() {
     log.debug "child fanlow"
	 parent.command(this,"fanlow") 
}
def fanhigh() {
     log.debug "child fanhigh"
	 parent.command(this,"fanhigh") 
}
def energysaver() {
     log.debug "child energysaver"
	 parent.command(this,"energysaver") 
}
def dry() {
     log.debug "child dry"
	 parent.command(this,"dry") 
}
def cool() {
     log.debug "child cool"
	 parent.command(this,"cool") 
}
def setRangedLevel(value) {
	log.debug "setting ranged level to $value"
	parent.commandValue(this, value)
    sendEvent(name:"level", value:value)
}

def custom1() {
    log.debug "child custom1()"
    parent.command(this, "custom1")
}

def custom2() {
    log.debug "child custom2()"
    parent.command(this, "custom2")
}

def custom3() {
    log.debug "child custom3()"
    parent.command(this, "custom3")
}

def custom4() {
    log.debug "child custom4()"
    parent.command(this, "custom4")
}

def custom5() {
    log.debug "child custom5()"
    parent.command(this, "custom5")
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

def on() {
	log.debug "child on()"
    sendEvent(name: "switch", value: "on")
	
    
	if (settings.numOnDelay == null || settings.numOnDelay == "" ) settings.numOnDelay = "5"
	runIn(Integer.parseInt(settings.numOnDelay),ondelay)
	
	log.debug "on done"
    

}

def momentaryOnHandler() {
	log.debug "momentaryOnHandler()"
    //sendEvent(name: "switch", value: "off")
	
}

def off() {
	log.debug "child off"
	parent.command(this, "power-off")
	log.debug "child off>ismoment: $momentaryOn,moment : settings.momentaryOnDelay, delay : $settings.momentaryOnDelay"
	if (settings.numOffDelay == null || settings.numOffDelay == "" ) settings.numOffDelay = "5"
	runIn(Integer.parseInt(settings.numOffDelay),offdelay)
	log.debug "off Done"
}
def ondelay()
{
	log.debug "ondelay()"
    parent.command(this, "power-on")
    if (momentaryOn) {
    	if (settings.momentaryOnDelay == null || settings.momentaryOnDelay == "" ) settings.momentaryOnDelay = "5"
    	log.debug "momentaryOnHandler() >> time : " + settings.momentaryOnDelay
    	runIn(Integer.parseInt(settings.momentaryOnDelay), momentaryOnHandler, [overwrite: true])
    }
    }
def FanBeforeOffDelay()
{
	log.debug "FanBeforeOffDelay Done"
    off()
}
def FanBeforeOff()
{energysaver()
	
	if (settings.FanModeBeforeOff)
	{
		if (settings.numFanModeBeforeOff == null || settings.numFanModeBeforeOff == "" ) settings.numFanModeBeforeOff = "300"
		runIn(Integer.parseInt(settings.numFanModeBeforeOff),FanBeforeOffDelay)
	}
	
}
def offdelay()
{
	log.debug "offdelay()"
    sendEvent(name: "switch", value: "off")
}
def virtualOn() {
	log.debug "child on()"	
    sendEvent(name: "switch", value: "on")
}

def virtualOff() {
	log.debug "child off"	
    sendEvent(name: "switch", value: "off")
}

def poll() {
	log.debug "poll()"
}

def parseEventData(Map results) {
    results.each { name, value ->
        //Parse events and optionally create SmartThings events
    }
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