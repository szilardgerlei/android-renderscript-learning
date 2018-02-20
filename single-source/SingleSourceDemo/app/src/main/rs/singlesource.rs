#pragma version(1)
#pragma rs java_package_name(com.gmail.szilard.gerlei.single.source.demo)

float RS_KERNEL add(float in) {
    float out = in + in / in * in / in * in / in / in * in / in * in / in;
    return out;
}

float RS_KERNEL mult(float in) {
    float out = in + in / in * in / in * in / in  / in * in / in * in / in;
    return out;
}

void RS_KERNEL printElements(float in, int x) {
     rsDebug("element" , in);
}

void process(rs_allocation inputImage, rs_allocation outputImage) {
    const uint32_t arrayLenght = rsAllocationGetDimX(inputImage);

    rs_allocation tmp = rsCreateAllocation_float(arrayLenght);

    long start = rsUptimeNanos();
    rsForEach(add, inputImage, tmp);
   // rsDebug(" ---- FIRST FOR_EACH RESULT PRINT ---- ", 0);
   // rsForEach(printElements, tmp);
    rsForEach(mult, tmp, outputImage);
   //  rsDebug(" ---- SECOND FOR_EACH RESULT PRINT ---- ", 0);
   // rsForEach(printElements, outputImage);
    long finish = rsUptimeNanos();
    rsDebug("Elapsed time", finish-start);
}