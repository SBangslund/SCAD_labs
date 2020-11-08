# SCAD P01 - Cloud Function Behaviour
By Samuel Bangslund (bangssam@students.zhaw.ch)

## E1: size of input data

## E2: configured memory allocation
The following data is produced by altering the allocated memory for the AWS lambda function. It varies from 128-2048 and the function does nearly no computation at all (fibonacci to 20). The allocation would presumably be more effective if the function was more computation heavy.

The function is called at each allocation for a 100 times with the following statistics:
| allocated | min | max  | mean | sum   |
|-----------|-----|------|------|-------|
| **128**   | 438 | 1614 | 515  | 20616 |
| **256**   | 434 | 2293 | 512  | 19472 |
| **512**   | 437 | 2302 | 506  | 19759 |
| **1024**  | 448 | 4935 | 585  | 25190 |
| **2048**  | 441 | 3341 | 535  | 22505 |

The result would presumably be more clear with an intense memory-algorithm. The time it takes to initially start the function (and its connection) is also slighty varying and can have some influence on the final results.

## E7: external network calls
## E8: processor affinity
Calling the E8 function yields the following system properties:
```json
{
  "availProc": 2,
  "freeMem": 4995952,
  "maxMem": 220987392,
  "totalMem": 8126464,
  "systemProperties": {
    "awt.toolkit": "sun.awt.X11.XToolkit",
    "java.specification.version": "11",
    "sun.cpu.isalist": "",
    "sun.jnu.encoding": "UTF-8",
    "java.class.path": "/var/runtime/lib/aws-lambda-java-core-1.2.0.jar:/var/runtime/lib/aws-lambda-java-runtime-0.2.0.jar:/var/runtime/lib/aws-lambda-java-serialization-0.2.0.jar",
    "java.vm.vendor": "Amazon.com Inc.",
    "sun.arch.data.model": "64",
    "java.vendor.url": "https://aws.amazon.com/corretto/",
    "user.timezone": "UTC",
    "os.name": "Linux",
    "java.vm.specification.version": "11",
    "sun.java.launcher": "SUN_STANDARD",
    "user.country": "US",
    "sun.boot.library.path": "/var/lang/lib",
    "sun.java.command": "lambdainternal.AWSLambda",
    "jdk.debug": "release",
    "sun.cpu.endian": "little",
    "user.home": "/home/sbx_user1051",
    "user.language": "en",
    "java.specification.vendor": "Oracle Corporation",
    "java.version.date": "2020-07-14",
    "java.home": "/var/lang",
    "file.separator": "/",
    "java.vm.compressedOopsMode": "32-bit",
    "line.separator": "\n",
    "java.specification.name": "Java Platform API Specification",
    "java.vm.specification.vendor": "Oracle Corporation",
    "java.awt.graphicsenv": "sun.awt.X11GraphicsEnvironment",
    "javax.net.ssl.trustStore": "/etc/pki/java/cacerts",
    "sun.management.compiler": "HotSpot 64-Bit Tiered Compilers",
    "java.runtime.version": "11.0.8+10-LTS",
    "user.name": "sbx_user1051",
    "java.net.preferIPv4Stack": "true",
    "path.separator": ":",
    "os.version": "4.14.193-110.317.amzn2.x86_64",
    "java.runtime.name": "OpenJDK Runtime Environment",
    "file.encoding": "UTF-8",
    "java.vm.name": "OpenJDK 64-Bit Server VM",
    "java.vendor.version": "Corretto-11.0.8.10.1",
    "java.vendor.url.bug": "https://github.com/corretto/corretto-11/issues/",
    "java.io.tmpdir": "/tmp",
    "java.version": "11.0.8",
    "user.dir": "/var/task",
    "os.arch": "amd64",
    "java.vm.specification.name": "Java Virtual Machine Specification",
    "java.awt.printerjob": "sun.print.PSPrinterJob",
    "sun.os.patch.level": "unknown",
    "java.library.path": "/var/lang/lib:/lib64:/usr/lib64:/var/runtime:/var/runtime/lib:/var/task:/var/task/lib:/opt/lib:/usr/java/packages/lib:/usr/lib64:/lib64:/lib:/usr/lib",
    "java.vm.info": "mixed mode, sharing",
    "java.vendor": "Amazon.com Inc.",
    "java.vm.version": "11.0.8+10-LTS",
    "sun.io.unicode.encoding": "UnicodeLittle",
    "java.class.version": "55.0"
  },
  "version": "11.0.8+10-LTS"
}
```
Which seems sufficfient for now. Calling this 100 times gives the following statistics: 
| allocated | min | max  | mean | sum   |
|-----------|-----|------|------|-------|
|    256    | 435 | 1892 | 522  | 20389 |

