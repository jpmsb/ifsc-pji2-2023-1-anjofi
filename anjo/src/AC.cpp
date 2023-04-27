#include "AC.h"

AC::AC(bool _alreadyOn, int pin) : sensor(pin){
    this->alreadyOn = _alreadyOn;
}

void AC::getValues(){
    previousTemperature = sensor.getCurrent();
    sensor.measure();
    currentTemperature = sensor.getCurrent();
}

bool AC::getStatus(){
    return currentTemperature < previousTemperature;
}

bool AC::getAlreadyOn(){
    return alreadyOn;
}

float AC::getCurrentTemperature(){
    return currentTemperature;
}

float AC::getPreviousTemperature(){
    return previousTemperature;
}
