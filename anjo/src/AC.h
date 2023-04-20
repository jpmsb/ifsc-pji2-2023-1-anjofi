#ifndef _AC_H_
#define _AC_H_

#include "Temperatura.h"

class AC {
    public:
       AC(bool alreadyOn = false, int pin = 12);

       void getValues();

       bool getStatus();

       float getCurrentTemperature();

       float getPreviousTemperature();

    private:
       float threshold = 1;
       float initialTemperature = 0;
       float currentTemperature;
       float previousTemperature;
       Temperatura sensor;
}; 

#endif
