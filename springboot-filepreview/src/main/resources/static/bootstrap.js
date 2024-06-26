﻿function receiveMessageString(n){"use strict";var t={};t.data=n;t.source="";receiveMessage(t)}function receiveMessage(n){"use strict";var r,s,a,h,f,c,v,y,p,w,l;if(localCompartment===null&&(localCompartment=n.source),MSG_TYPE_ADPARAMS===n.data.substr(0,MSG_TYPE_ADPARAMS.length)){if(r=n.data.substr(MSG_TYPE_ADPARAMS.length+1),r!=="")try{adParams=JSON.parse(r)}catch(e){reportAdError("Ad hutool JSON parse error: "+e.message)}}else if(MSG_TYPE_PRMPARAMS===n.data.substr(0,MSG_TYPE_PRMPARAMS.length)){if(r=n.data.substr(MSG_TYPE_PRMPARAMS.length+1),r!=="")try{prmParams=JSON.parse(r)}catch(e){reportAdError("Prm hutool JSON parse error: "+e.message)}}else if(MSG_TYPE_APPPARAMS===n.data.substr(0,MSG_TYPE_APPPARAMS.length)){if(r=n.data.substr(MSG_TYPE_APPPARAMS.length+1),r!=="")try{appParams=JSON.parse(r)}catch(e){reportAdError("App options JSON parse error: "+e.message)}}else if(MSG_TYPE_SETID===n.data.substr(0,MSG_TYPE_SETID.length))adControlId=n.data.substr(MSG_TYPE_SETID.length+1);else if(MSG_TYPE_INIT!==n.data.substr(0,MSG_TYPE_INIT.length)&&typeof Ormma!="undefined"&&Ormma!==null){var u=n.data,t=null,i=null,o=u.indexOf(":");o<0?t=u:(t=u.substr(0,o),i=u.substr(o+1));t===MSG_TYPE_SETSIZE?(s=JSON.parse(i),Ormma._setSize(s.width,s.height)):t===MSG_TYPE_SETMAXSIZE?(a=JSON.parse(i),Ormma._maxSize=a):t===MSG_TYPE_SETSCREENSIZE?(h=JSON.parse(i),Ormma._setScreenSize(h.width,h.height)):t===MSG_TYPE_SETSTATE?Ormma._setState(i):t===MSG_TYPE_SETLOCALE?Ormma._setLocale(i):t===MSG_TYPE_SETSDKINFO?(f=JSON.parse(i),Ormma._setSdkVersion(f.sdkVersion,f.client,f.runtimeType)):t===MSG_TYPE_SETCAPABILITY?(c=JSON.parse(i),Ormma._setCapability(c.capability,c.value)):t===MSG_TYPE_SETPLACEMENTTYPE?Ormma._setPlacementType(i):t===MSG_TYPE_START?Ormma._init(i):t===MSG_TYPE_FIRESHAKE?Ormma._shake():t===MSG_TYPE_ORMMA_RESPONSE?Ormma._sendResponse(JSON.parse(i)):t===MSG_TYPE_UPDATETILTCOORDS?(v=JSON.parse(i),Ormma._tiltChange(v)):t===MSG_TYPE_VIEWABLECHANGE?(y=JSON.parse(i),Ormma._viewableChange(y.viewable)):t===MSG_TYPE_UPDATEORIENTATION?(p=JSON.parse(i),Ormma._setOrientation(p.orientation)):t===MSG_TYPE_SETNETWORK?Ormma._setNetwork(i):t===MSG_TYPE_WIREAPPEVENTS?(w=JSON.parse(i),_wireApplicationEvents(w)):t===MSG_TYPE_ERROR?(l=JSON.parse(i),Ormma._throwError(l.action,l.message)):t===MSG_TYPE_SETFOCUS&&i==="true"&&document.body.focus()}}function reportAdError(n){"use strict";console.log(n);postToLocal(MSG_TYPE_ERROR+":"+n)}function postToLocal(n){"use strict";localCompartment!=null&&window.external.notify(n)}function _wireApplicationEvents(n){"use strict";if(n!==null&&typeof n!="undefined"){var t=parseInt(n.events);(t&EVENT_TYPE_ENUM.PointerDown)!=0&&typeof applicationEventHandlers.onPointerDown!="function"?(applicationEventHandlers.onPointerDown=function(t){t.which===2&&n.preventDefault&&t.preventDefault();pointerDown=!0;postToLocal(MSG_TYPE_ONPOINTERDOWN+":"+JSON.stringify({clientX:t.clientX,clientY:t.clientY,pointerType:t.pointerType,which:t.which}))},window.addEventListener("MSPointerDown",applicationEventHandlers.onPointerDown,!1)):(t&EVENT_TYPE_ENUM.PointerDown)==0&&typeof applicationEventHandlers.onPointerDown=="function"&&(window.removeEventListener("MSPointerDown",applicationEventHandlers.onPointerDown,!1),applicationEventHandlers.onPointerDown=null);(t&EVENT_TYPE_ENUM.PointerUp)!=0&&typeof applicationEventHandlers.onPointerUp!="function"?(applicationEventHandlers.onPointerUp=function(n){n.type==="pointerout"?pointerDown&&(pointerDown=!1,postToLocal(MSG_TYPE_ONPOINTERUP)):(pointerDown=!1,postToLocal(MSG_TYPE_ONPOINTERUP))},window.addEventListener("MSPointerUp",applicationEventHandlers.onPointerUp,!1),window.addEventListener("MSPointerCancel",applicationEventHandlers.onPointerUp,!1),window.addEventListener("MSPointerOut",applicationEventHandlers.onPointerUp,!1),window.addEventListener("MSLostPointerCapture",applicationEventHandlers.onPointerUp,!1)):(t&EVENT_TYPE_ENUM.PointerUp)==0&&typeof applicationEventHandlers.onPointerUp=="function"&&(window.removeEventListener("MSPointerUp",applicationEventHandlers.onPointerUp,!1),window.removeEventListener("MSPointerCancel",applicationEventHandlers.onPointerUp,!1),window.removeEventListener("MSPointerOut",applicationEventHandlers.onPointerUp,!1),window.removeEventListener("MSLostPointerCapture",applicationEventHandlers.onPointerUp,!1),applicationEventHandlers.onPointerUp=null);(t&EVENT_TYPE_ENUM.PointerMove)!=0&&typeof applicationEventHandlers.onPointerMove!="function"?(applicationEventHandlers.onPointerMove=function(n){postToLocal(MSG_TYPE_ONPOINTERMOVE+":"+JSON.stringify({clientX:n.clientX,clientY:n.clientY}))},window.addEventListener("MSPointerMove",applicationEventHandlers.onPointerMove,!1)):(t&EVENT_TYPE_ENUM.PointerMove)==0&&typeof applicationEventHandlers.onPointerMove=="function"&&(window.removeEventListener("MSPointerMove",applicationEventHandlers.onPointerMove,!1),applicationEventHandlers.onPointerMove=null);(t&EVENT_TYPE_ENUM.ManipulationStateChanged)!=0&&typeof applicationEventHandlers.onManipulationStateChanged!="function"?(applicationEventHandlers.onManipulationStateChanged=function(n){postToLocal(MSG_TYPE_ONMANIPSTATECHANGED+":"+JSON.stringify({lastState:n.lastState,currentState:n.currentState}))},window.addEventListener("MSManipulationStateChanged",applicationEventHandlers.onManipulationStateChanged,!1)):(t&EVENT_TYPE_ENUM.ManipulationStateChanged)==0&&typeof applicationEventHandlers.onManipulationStateChanged=="function"&&(window.removeEventListener("MSManipulationStateChanged",applicationEventHandlers.onManipulationStateChanged,!1),applicationEventHandlers.onManipulationStateChanged=null);(t&EVENT_TYPE_ENUM.MouseWheel)!=0&&typeof applicationEventHandlers.onMouseWheel!="function"?(applicationEventHandlers.onMouseWheel=function(t){n.preventDefault&&t.preventDefault();postToLocal(MSG_TYPE_ONMOUSEWHEEL+":"+JSON.stringify({clientX:t.clientX,clientY:t.clientY,ctrlKey:t.ctrlKey,wheelDelta:t.wheelDelta}))},window.addEventListener("mousewheel",applicationEventHandlers.onMouseWheel,!1)):(t&EVENT_TYPE_ENUM.MouseWheel)==0&&typeof applicationEventHandlers.onMouseWheel=="function"&&(window.removeEventListener("mousewheel",applicationEventHandlers.onMouseWheel,!1),applicationEventHandlers.onMouseWheel=null)}}window.onerror=function(n,t,i){"use strict";try{console.error("[msg:"+n+"][url:"+t+"][lineNumber:"+i+"]");window.external.notify("error:unhandled ad exception: "+n)}catch(r){}return!0};var localCompartment=null,adControlId=null,pointerDown,adParams=null,prmParams=null,appParams=null,MSG_TYPE_ADPARAMS="adParams",MSG_TYPE_PRMPARAMS="prmParams",MSG_TYPE_APPPARAMS="appParams",MSG_TYPE_INIT="init",MSG_TYPE_SETID="setId",MSG_TYPE_ERROR="error",MSG_TYPE_SETMAXSIZE="setMaxSize",MSG_TYPE_SETSCREENSIZE="setScreenSize",MSG_TYPE_SETSIZE="setSize",MSG_TYPE_SETSTATE="setState",MSG_TYPE_SETPLACEMENTTYPE="setPlacementType",MSG_TYPE_WIREAPPEVENTS="wireAppEvents",MSG_TYPE_START="ormmaStart",MSG_TYPE_ORMMA_RESPONSE="ormmaResponse",MSG_TYPE_FIRESHAKE="fireShake",MSG_TYPE_UPDATETILTCOORDS="updateTiltCoords",MSG_TYPE_UPDATEORIENTATION="updateOrienation",MSG_TYPE_VIEWABLECHANGE="viewableChange",MSG_TYPE_SETNETWORK="setNetwork",MSG_TYPE_SETLOCALE="setLocale",MSG_TYPE_SETSDKINFO="setSdkInfo",MSG_TYPE_SETCAPABILITY="setCapability",MSG_TYPE_SETFOCUS="setFocus",MSG_TYPE_ONPOINTERDOWN="MSPointerDown",MSG_TYPE_ONPOINTERUP="MSPointerUp",MSG_TYPE_ONPOINTERMOVE="MSPointerMove",MSG_TYPE_ONMOUSEWHEEL="MSMouseWheel",MSG_TYPE_ONMANIPSTATECHANGED="MSManipulationStateChanged",EVENT_TYPE_ENUM={All:-1,PointerDown:1,PointerUp:2,PointerMove:4,MouseWheel:16,ManipulationStateChanged:32},applicationEventHandlers={onPointerDown:null,onPointerUp:null,onMouseWheel:null,onPointerMove:null,onManipulationStateChanged:null};
// SIG // Begin signature block
// SIG // MIIddAYJKoZIhvcNAQcCoIIdZTCCHWECAQExCzAJBgUr
// SIG // DgMCGgUAMGcGCisGAQQBgjcCAQSgWTBXMDIGCisGAQQB
// SIG // gjcCAR4wJAIBAQQQEODJBs441BGiowAQS9NQkAIBAAIB
// SIG // AAIBAAIBAAIBADAhMAkGBSsOAwIaBQAEFKpjhl5i8bQd
// SIG // HBrsOnhMdcMSqlmFoIIYUjCCBMIwggOqoAMCAQICEzMA
// SIG // AAC/kWz7fBok4CIAAAAAAL8wDQYJKoZIhvcNAQEFBQAw
// SIG // dzELMAkGA1UEBhMCVVMxEzARBgNVBAgTCldhc2hpbmd0
// SIG // b24xEDAOBgNVBAcTB1JlZG1vbmQxHjAcBgNVBAoTFU1p
// SIG // Y3Jvc29mdCBDb3Jwb3JhdGlvbjEhMB8GA1UEAxMYTWlj
// SIG // cm9zb2Z0IFRpbWUtU3RhbXAgUENBMB4XDTE2MDkwNzE3
// SIG // NTg0OVoXDTE4MDkwNzE3NTg0OVowgbIxCzAJBgNVBAYT
// SIG // AlVTMRMwEQYDVQQIEwpXYXNoaW5ndG9uMRAwDgYDVQQH
// SIG // EwdSZWRtb25kMR4wHAYDVQQKExVNaWNyb3NvZnQgQ29y
// SIG // cG9yYXRpb24xDDAKBgNVBAsTA0FPQzEnMCUGA1UECxMe
// SIG // bkNpcGhlciBEU0UgRVNOOjU3QzgtMkQxNS0xQzhCMSUw
// SIG // IwYDVQQDExxNaWNyb3NvZnQgVGltZS1TdGFtcCBTZXJ2
// SIG // aWNlMIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKC
// SIG // AQEAre1/hsD2onVXFU0U9sqIVfy9XaUzAr3+Ax5QmNBl
// SIG // JoRMw+GJ9tqv6p6mUxw3fCJSPzh7yoVgvZLChBfgYAR7
// SIG // VED0oO7OvmV8uval+uK+pu1/zWIs3it9Q7EnC7Ax56rG
// SIG // 58CWjWdgtruYYXCrB73PAWkrL/5ypa+vnoNol9svWotb
// SIG // gdckjXrpxLXX3IQK9VOkG+wCR61zE3SpfKbS6L+u2hVN
// SIG // K0UTMECTlh0fqP4EGvb+M/3+N0WNj5zEaBew87Z+nYra
// SIG // EPQoE4jHUjLbKhVjAEDTLeW2fKGNYZoJEgzehcE41clB
// SIG // BOrdyibYF9oTB4zlarR10JbndBkd2S7QDjG0uwIDAQAB
// SIG // o4IBCTCCAQUwHQYDVR0OBBYEFORsGjkax5NYtEY2ZQfE
// SIG // ALd5F5wRMB8GA1UdIwQYMBaAFCM0+NlSRnAK7UD7dvuz
// SIG // K7DDNbMPMFQGA1UdHwRNMEswSaBHoEWGQ2h0dHA6Ly9j
// SIG // cmwubWljcm9zb2Z0LmNvbS9wa2kvY3JsL3Byb2R1Y3Rz
// SIG // L01pY3Jvc29mdFRpbWVTdGFtcFBDQS5jcmwwWAYIKwYB
// SIG // BQUHAQEETDBKMEgGCCsGAQUFBzAChjxodHRwOi8vd3d3
// SIG // Lm1pY3Jvc29mdC5jb20vcGtpL2NlcnRzL01pY3Jvc29m
// SIG // dFRpbWVTdGFtcFBDQS5jcnQwEwYDVR0lBAwwCgYIKwYB
// SIG // BQUHAwgwDQYJKoZIhvcNAQEFBQADggEBAI7eto3GftmA
// SIG // apu0SorfNuw0VPTioqIuhxBEU9F9Gy/iY1E7t+5OKYj3
// SIG // Ctyf9HH5LVrj462ErskmvzW5JpbwIUFeFaaFsYqcd3Ga
// SIG // ZRfRyvSB+LfAA2A8VCObtcW9q9uGhMRiM1mPtbh/dm80
// SIG // ymwI7sDc0zENc9dCnGuF1zyOBxWWpV8YC324wpFdyCjb
// SIG // KVNUyC1dA85NLXSgMMfzKHmTdRxXAfVo826RZwlR/jsv
// SIG // zcniTsojROLk/+osh89flwqn9pyK1fw117CWCsIn1ZTr
// SIG // 0ieGa1eNDGLnKt5VkLihEX14ryxV5XSsl0K0uHnk6ET0
// SIG // nr0YrHNBgWLmXM6ZHXjeX2gwggX/MIID56ADAgECAhMz
// SIG // AAABA14lHJkfox64AAAAAAEDMA0GCSqGSIb3DQEBCwUA
// SIG // MH4xCzAJBgNVBAYTAlVTMRMwEQYDVQQIEwpXYXNoaW5n
// SIG // dG9uMRAwDgYDVQQHEwdSZWRtb25kMR4wHAYDVQQKExVN
// SIG // aWNyb3NvZnQgQ29ycG9yYXRpb24xKDAmBgNVBAMTH01p
// SIG // Y3Jvc29mdCBDb2RlIFNpZ25pbmcgUENBIDIwMTEwHhcN
// SIG // MTgwNzEyMjAwODQ4WhcNMTkwNzI2MjAwODQ4WjB0MQsw
// SIG // CQYDVQQGEwJVUzETMBEGA1UECBMKV2FzaGluZ3RvbjEQ
// SIG // MA4GA1UEBxMHUmVkbW9uZDEeMBwGA1UEChMVTWljcm9z
// SIG // b2Z0IENvcnBvcmF0aW9uMR4wHAYDVQQDExVNaWNyb3Nv
// SIG // ZnQgQ29ycG9yYXRpb24wggEiMA0GCSqGSIb3DQEBAQUA
// SIG // A4IBDwAwggEKAoIBAQDRlHY25oarNv5p+UZ8i4hQy5Bw
// SIG // f7BVqSQdfjnnBZ8PrHuXss5zCvvUmyRcFrU53Rt+M2wR
// SIG // /Dsm85iqXVNrqsPsE7jS789Xf8xly69NLjKxVitONAeJ
// SIG // /mkhvT5E+94SnYW/fHaGfXKxdpth5opkTEbOttU6jHeT
// SIG // d2chnLZaBl5HhvU80QnKDT3NsumhUHjRhIjiATwi/K+W
// SIG // CMxdmcDt66VamJL1yEBOanOv3uN0etNfRpe84mcod5ms
// SIG // wQ4xFo8ADwH+S15UD8rEZT8K46NG2/YsAzoZvmgFFpzm
// SIG // fzS/p4eNZTkmyWPU78XdvSX+/Sj0NIZ5rCrVXzCRO+QU
// SIG // auuxygQjAgMBAAGjggF+MIIBejAfBgNVHSUEGDAWBgor
// SIG // BgEEAYI3TAgBBggrBgEFBQcDAzAdBgNVHQ4EFgQUR77A
// SIG // y+GmP/1l1jjyA123r3f3QP8wUAYDVR0RBEkwR6RFMEMx
// SIG // KTAnBgNVBAsTIE1pY3Jvc29mdCBPcGVyYXRpb25zIFB1
// SIG // ZXJ0byBSaWNvMRYwFAYDVQQFEw0yMzAwMTIrNDM3OTY1
// SIG // MB8GA1UdIwQYMBaAFEhuZOVQBdOCqhc3NyK1bajKdQKV
// SIG // MFQGA1UdHwRNMEswSaBHoEWGQ2h0dHA6Ly93d3cubWlj
// SIG // cm9zb2Z0LmNvbS9wa2lvcHMvY3JsL01pY0NvZFNpZ1BD
// SIG // QTIwMTFfMjAxMS0wNy0wOC5jcmwwYQYIKwYBBQUHAQEE
// SIG // VTBTMFEGCCsGAQUFBzAChkVodHRwOi8vd3d3Lm1pY3Jv
// SIG // c29mdC5jb20vcGtpb3BzL2NlcnRzL01pY0NvZFNpZ1BD
// SIG // QTIwMTFfMjAxMS0wNy0wOC5jcnQwDAYDVR0TAQH/BAIw
// SIG // ADANBgkqhkiG9w0BAQsFAAOCAgEAn/XJUw0/DSbsokTY
// SIG // DdGfY5YGSz8eXMUzo6TDbK8fwAG662XsnjMQD6esW9S9
// SIG // kGEX5zHnwya0rPUn00iThoj+EjWRZCLRay07qCwVlCnS
// SIG // N5bmNf8MzsgGFhaeJLHiOfluDnjYDBu2KWAndjQkm925
// SIG // l3XLATutghIWIoCJFYS7mFAgsBcmhkmvzn1FFUM0ls+B
// SIG // XBgs1JPyZ6vic8g9o838Mh5gHOmwGzD7LLsHLpaEk0Uo
// SIG // VFzNlv2g24HYtjDKQ7HzSMCyRhxdXnYqWJ/U7vL0+khM
// SIG // tWGLsIxB6aq4nZD0/2pCD7k+6Q7slPyNgLt44yOneFuy
// SIG // bR/5WcF9ttE5yXnggxxgCto9sNHtNr9FB+kbNm7lPTsF
// SIG // A6fUpyUSj+Z2oxOzRVpDMYLa2ISuubAfdfX2HX1RETcn
// SIG // 6LU1hHH3V6qu+olxyZjSnlpkdr6Mw30VapHxFPTy2TUx
// SIG // uNty+rR1yIibar+YRcdmstf/zpKQdeTr5obSyBvbJ8Bb
// SIG // lW9Jb1hdaSreU0v46Mp79mwV+QMZDxGFqk+av6pX3WDG
// SIG // 9XEg9FGomsrp0es0Rz11+iLsVT9qGTlrEOlaP470I3gw
// SIG // svKmOMs1jaqYWSRAuDpnpAdfoP7YO0kT+wzh7Qttg1DO
// SIG // 8H8+4NkI6IwhSkHC3uuOW+4Dwx1ubuZUNWZncnwa6lL2
// SIG // IsRyP64wggYHMIID76ADAgECAgphFmg0AAAAAAAcMA0G
// SIG // CSqGSIb3DQEBBQUAMF8xEzARBgoJkiaJk/IsZAEZFgNj
// SIG // b20xGTAXBgoJkiaJk/IsZAEZFgltaWNyb3NvZnQxLTAr
// SIG // BgNVBAMTJE1pY3Jvc29mdCBSb290IENlcnRpZmljYXRl
// SIG // IEF1dGhvcml0eTAeFw0wNzA0MDMxMjUzMDlaFw0yMTA0
// SIG // MDMxMzAzMDlaMHcxCzAJBgNVBAYTAlVTMRMwEQYDVQQI
// SIG // EwpXYXNoaW5ndG9uMRAwDgYDVQQHEwdSZWRtb25kMR4w
// SIG // HAYDVQQKExVNaWNyb3NvZnQgQ29ycG9yYXRpb24xITAf
// SIG // BgNVBAMTGE1pY3Jvc29mdCBUaW1lLVN0YW1wIFBDQTCC
// SIG // ASIwDQYJKoZIhvcNAQEBBQADggEPADCCAQoCggEBAJ+h
// SIG // bLHf20iSKnxrLhnhveLjxZlRI1Ctzt0YTiQP7tGn0Uyt
// SIG // dDAgEesH1VSVFUmUG0KSrphcMCbaAGvoe73siQcP9w4E
// SIG // mPCJzB/LMySHnfL0Zxws/HvniB3q506jocEjU8qN+kXP
// SIG // CdBer9CwQgSi+aZsk2fXKNxGU7CG0OUoRi4nrIZPVVIM
// SIG // 5AMs+2qQkDBuh/NZMJ36ftaXs+ghl3740hPzCLdTbVK0
// SIG // RZCfSABKR2YRJylmqJfk0waBSqL5hKcRRxQJgp+E7VV4
// SIG // /gGaHVAIhQAQMEbtt94jRrvELVSfrx54QTF3zJvfO4OT
// SIG // oWECtR0Nsfz3m7IBziJLVP/5BcPCIAsCAwEAAaOCAasw
// SIG // ggGnMA8GA1UdEwEB/wQFMAMBAf8wHQYDVR0OBBYEFCM0
// SIG // +NlSRnAK7UD7dvuzK7DDNbMPMAsGA1UdDwQEAwIBhjAQ
// SIG // BgkrBgEEAYI3FQEEAwIBADCBmAYDVR0jBIGQMIGNgBQO
// SIG // rIJgQFYnl+UlE/wq4QpTlVnkpKFjpGEwXzETMBEGCgmS
// SIG // JomT8ixkARkWA2NvbTEZMBcGCgmSJomT8ixkARkWCW1p
// SIG // Y3Jvc29mdDEtMCsGA1UEAxMkTWljcm9zb2Z0IFJvb3Qg
// SIG // Q2VydGlmaWNhdGUgQXV0aG9yaXR5ghB5rRahSqClrUxz
// SIG // WPQHEy5lMFAGA1UdHwRJMEcwRaBDoEGGP2h0dHA6Ly9j
// SIG // cmwubWljcm9zb2Z0LmNvbS9wa2kvY3JsL3Byb2R1Y3Rz
// SIG // L21pY3Jvc29mdHJvb3RjZXJ0LmNybDBUBggrBgEFBQcB
// SIG // AQRIMEYwRAYIKwYBBQUHMAKGOGh0dHA6Ly93d3cubWlj
// SIG // cm9zb2Z0LmNvbS9wa2kvY2VydHMvTWljcm9zb2Z0Um9v
// SIG // dENlcnQuY3J0MBMGA1UdJQQMMAoGCCsGAQUFBwMIMA0G
// SIG // CSqGSIb3DQEBBQUAA4ICAQAQl4rDXANENt3ptK132855
// SIG // UU0BsS50cVttDBOrzr57j7gu1BKijG1iuFcCy04gE1CZ
// SIG // 3XpA4le7r1iaHOEdAYasu3jyi9DsOwHu4r6PCgXIjUji
// SIG // 8FMV3U+rkuTnjWrVgMHmlPIGL4UD6ZEqJCJw+/b85HiZ
// SIG // Lg33B+JwvBhOnY5rCnKVuKE5nGctxVEO6mJcPxaYiyA/
// SIG // 4gcaMvnMMUp2MT0rcgvI6nA9/4UKE9/CCmGO8Ne4F+tO
// SIG // i3/FNSteo7/rvH0LQnvUU3Ih7jDKu3hlXFsBFwoUDtLa
// SIG // FJj1PLlmWLMtL+f5hYbMUVbonXCUbKw5TNT2eb+qGHpi
// SIG // Ke+imyk0BncaYsk9Hm0fgvALxyy7z0Oz5fnsfbXjpKh0
// SIG // NbhOxXEjEiZ2CzxSjHFaRkMUvLOzsE1nyJ9C/4B5IYCe
// SIG // FTBm6EISXhrIniIh0EPpK+m79EjMLNTYMoBMJipIJF9a
// SIG // 6lbvpt6Znco6b72BJ3QGEe52Ib+bgsEnVLaxaj2JoXZh
// SIG // tG6hE6a/qkfwEm/9ijJssv7fUciMI8lmvZ0dhxJkAj0t
// SIG // r1mPuOQh5bWwymO0eFQF1EEuUKyUsKV4q7OglnUa2ZKH
// SIG // E3UiLzKoCG6gW4wlv6DvhMoh1useT8ma7kng9wFlb4kL
// SIG // fchpyOZu6qeXzjEp/w7FW1zYTRuh2Povnj8uVRZryROj
// SIG // /TCCB3owggVioAMCAQICCmEOkNIAAAAAAAMwDQYJKoZI
// SIG // hvcNAQELBQAwgYgxCzAJBgNVBAYTAlVTMRMwEQYDVQQI
// SIG // EwpXYXNoaW5ndG9uMRAwDgYDVQQHEwdSZWRtb25kMR4w
// SIG // HAYDVQQKExVNaWNyb3NvZnQgQ29ycG9yYXRpb24xMjAw
// SIG // BgNVBAMTKU1pY3Jvc29mdCBSb290IENlcnRpZmljYXRl
// SIG // IEF1dGhvcml0eSAyMDExMB4XDTExMDcwODIwNTkwOVoX
// SIG // DTI2MDcwODIxMDkwOVowfjELMAkGA1UEBhMCVVMxEzAR
// SIG // BgNVBAgTCldhc2hpbmd0b24xEDAOBgNVBAcTB1JlZG1v
// SIG // bmQxHjAcBgNVBAoTFU1pY3Jvc29mdCBDb3Jwb3JhdGlv
// SIG // bjEoMCYGA1UEAxMfTWljcm9zb2Z0IENvZGUgU2lnbmlu
// SIG // ZyBQQ0EgMjAxMTCCAiIwDQYJKoZIhvcNAQEBBQADggIP
// SIG // ADCCAgoCggIBAKvw+nIQHC6t2G6qghBNNLrytlghn0Ib
// SIG // KmvpWlCquAY4GgRJun/DDB7dN2vGEtgL8DjCmQawyDnV
// SIG // ARQxQtOJDXlkh36UYCRsr55JnOloXtLfm1OyCizDr9mp
// SIG // K656Ca/XllnKYBoF6WZ26DJSJhIv56sIUM+zRLdd2MQu
// SIG // A3WraPPLbfM6XKEW9Ea64DhkrG5kNXimoGMPLdNAk/jj
// SIG // 3gcN1Vx5pUkp5w2+oBN3vpQ97/vjK1oQH01WKKJ6cuAS
// SIG // OrdJXtjt7UORg9l7snuGG9k+sYxd6IlPhBryoS9Z5JA7
// SIG // La4zWMW3Pv4y07MDPbGyr5I4ftKdgCz1TlaRITUlwzlu
// SIG // ZH9TupwPrRkjhMv0ugOGjfdf8NBSv4yUh7zAIXQlXxgo
// SIG // tswnKDglmDlKNs98sZKuHCOnqWbsYR9q4ShJnV+I4iVd
// SIG // 0yFLPlLEtVc/JAPw0XpbL9Uj43BdD1FGd7P4AOG8rAKC
// SIG // X9vAFbO9G9RVS+c5oQ/pI0m8GLhEfEXkwcNyeuBy5yTf
// SIG // v0aZxe/CHFfbg43sTUkwp6uO3+xbn6/83bBm4sGXgXvt
// SIG // 1u1L50kppxMopqd9Z4DmimJ4X7IvhNdXnFy/dygo8e1t
// SIG // wyiPLI9AN0/B4YVEicQJTMXUpUMvdJX3bvh4IFgsE11g
// SIG // lZo+TzOE2rCIF96eTvSWsLxGoGyY0uDWiIwLAgMBAAGj
// SIG // ggHtMIIB6TAQBgkrBgEEAYI3FQEEAwIBADAdBgNVHQ4E
// SIG // FgQUSG5k5VAF04KqFzc3IrVtqMp1ApUwGQYJKwYBBAGC
// SIG // NxQCBAweCgBTAHUAYgBDAEEwCwYDVR0PBAQDAgGGMA8G
// SIG // A1UdEwEB/wQFMAMBAf8wHwYDVR0jBBgwFoAUci06AjGQ
// SIG // Q7kUBU7h6qfHMdEjiTQwWgYDVR0fBFMwUTBPoE2gS4ZJ
// SIG // aHR0cDovL2NybC5taWNyb3NvZnQuY29tL3BraS9jcmwv
// SIG // cHJvZHVjdHMvTWljUm9vQ2VyQXV0MjAxMV8yMDExXzAz
// SIG // XzIyLmNybDBeBggrBgEFBQcBAQRSMFAwTgYIKwYBBQUH
// SIG // MAKGQmh0dHA6Ly93d3cubWljcm9zb2Z0LmNvbS9wa2kv
// SIG // Y2VydHMvTWljUm9vQ2VyQXV0MjAxMV8yMDExXzAzXzIy
// SIG // LmNydDCBnwYDVR0gBIGXMIGUMIGRBgkrBgEEAYI3LgMw
// SIG // gYMwPwYIKwYBBQUHAgEWM2h0dHA6Ly93d3cubWljcm9z
// SIG // b2Z0LmNvbS9wa2lvcHMvZG9jcy9wcmltYXJ5Y3BzLmh0
// SIG // bTBABggrBgEFBQcCAjA0HjIgHQBMAGUAZwBhAGwAXwBw
// SIG // AG8AbABpAGMAeQBfAHMAdABhAHQAZQBtAGUAbgB0AC4g
// SIG // HTANBgkqhkiG9w0BAQsFAAOCAgEAZ/KGpZjgVHkaLtPY
// SIG // dGcimwuWEeFjkplCln3SeQyQwWVfLiw++MNy0W2D/r4/
// SIG // 6ArKO79HqaPzadtjvyI1pZddZYSQfYtGUFXYDJJ80hpL
// SIG // HPM8QotS0LD9a+M+By4pm+Y9G6XUtR13lDni6WTJRD14
// SIG // eiPzE32mkHSDjfTLJgJGKsKKELukqQUMm+1o+mgulaAq
// SIG // PyprWEljHwlpblqYluSD9MCP80Yr3vw70L01724lruWv
// SIG // J+3Q3fMOr5kol5hNDj0L8giJ1h/DMhji8MUtzluetEk5
// SIG // CsYKwsatruWy2dsViFFFWDgycScaf7H0J/jeLDogaZiy
// SIG // WYlobm+nt3TDQAUGpgEqKD6CPxNNZgvAs0314Y9/HG8V
// SIG // fUWnduVAKmWjw11SYobDHWM2l4bf2vP48hahmifhzaWX
// SIG // 0O5dY0HjWwechz4GdwbRBrF1HxS+YWG18NzGGwS+30HH
// SIG // Diju3mUv7Jf2oVyW2ADWoUa9WfOXpQlLSBCZgB/QACnF
// SIG // sZulP0V3HjXG0qKin3p6IvpIlR+r+0cjgPWe+L9rt0uX
// SIG // 4ut1eBrs6jeZeRhL/9azI2h15q/6/IvrC4DqaTuv/DDt
// SIG // BEyO3991bWORPdGdVk5Pv4BXIqF4ETIheu9BCrE/+6jM
// SIG // pF3BoYibV3FWTkhFwELJm3ZbCoBIa/15n8G9bW1qyVJz
// SIG // Ew16UM0xggSOMIIEigIBATCBlTB+MQswCQYDVQQGEwJV
// SIG // UzETMBEGA1UECBMKV2FzaGluZ3RvbjEQMA4GA1UEBxMH
// SIG // UmVkbW9uZDEeMBwGA1UEChMVTWljcm9zb2Z0IENvcnBv
// SIG // cmF0aW9uMSgwJgYDVQQDEx9NaWNyb3NvZnQgQ29kZSBT
// SIG // aWduaW5nIFBDQSAyMDExAhMzAAABA14lHJkfox64AAAA
// SIG // AAEDMAkGBSsOAwIaBQCggaIwGQYJKoZIhvcNAQkDMQwG
// SIG // CisGAQQBgjcCAQQwHAYKKwYBBAGCNwIBCzEOMAwGCisG
// SIG // AQQBgjcCARUwIwYJKoZIhvcNAQkEMRYEFPGULQrAoTX2
// SIG // U5VlT2ka11qftQ8fMEIGCisGAQQBgjcCAQwxNDAyoBSA
// SIG // EgBNAGkAYwByAG8AcwBvAGYAdKEagBhodHRwOi8vd3d3
// SIG // Lm1pY3Jvc29mdC5jb20wDQYJKoZIhvcNAQEBBQAEggEA
// SIG // YaNXzJhuGZbLZyIFvGLFHzdFJTPCJSTP8cxVEOAffBgM
// SIG // Aot1iuwdOlYfa/AqfRvn8pFpPolrhpztue5CFOAXc8bM
// SIG // lvE5EHac+MZnpd/exmz/AvC70hs/37JKCZVXGJTdETo2
// SIG // E/riM2EEuJbgY/B3rtFLcXUgyqdTYm3YEc95ZysKqej/
// SIG // in1FfvUFHlJ4BR+C6uqUobKzJios4rfDNWrCINPMZtq3
// SIG // aw8sP0LhsXOgTV3c03ajb+xYTPwkHfP3P0f70+c9so7D
// SIG // l336mZe9xpsoiw50gNEZu/mSNOJBhqBVI8gQO/0IbmSq
// SIG // qL/vF089aZ5B/LHqZFNw6pLHOO2341ZsC6GCAigwggIk
// SIG // BgkqhkiG9w0BCQYxggIVMIICEQIBATCBjjB3MQswCQYD
// SIG // VQQGEwJVUzETMBEGA1UECBMKV2FzaGluZ3RvbjEQMA4G
// SIG // A1UEBxMHUmVkbW9uZDEeMBwGA1UEChMVTWljcm9zb2Z0
// SIG // IENvcnBvcmF0aW9uMSEwHwYDVQQDExhNaWNyb3NvZnQg
// SIG // VGltZS1TdGFtcCBQQ0ECEzMAAAC/kWz7fBok4CIAAAAA
// SIG // AL8wCQYFKw4DAhoFAKBdMBgGCSqGSIb3DQEJAzELBgkq
// SIG // hkiG9w0BBwEwHAYJKoZIhvcNAQkFMQ8XDTE4MDgyMjE4
// SIG // MDgyNlowIwYJKoZIhvcNAQkEMRYEFBwakoUGQTAoammU
// SIG // XIeluvpyuC6EMA0GCSqGSIb3DQEBBQUABIIBABCaT66E
// SIG // +8ZG5TVHDAlYFEROrh6dTuOpol7hWwvGsv3wjb9n7CwJ
// SIG // Y9D1tA3Wz+Juw3EhUDuyDMyFeUYdc07io2j4DEmqxcct
// SIG // 4XMJJwSFpSjjgvsU2aOf2VZXHnmSDxKzXfjlfxDtk7j7
// SIG // OEQr5Z3rjACDC/wse3+0tPca24/YzFp0G49AR/5e5Wl5
// SIG // 6DbOtqA6pMKgcZg+zkSSMQ2ueiXEXykKzU6EG4vwPrNk
// SIG // 4XB2KVnQRxViw6kDWJ3qjFLl19qF5R2ipPgQgbjTcFo+
// SIG // rhJwE/Bd9BM1tLuAEXRYiOIFGghQv2J90CT/7SbjpOQw
// SIG // kyfmmVv3dWy+NdctoSS6F18uHq0=
// SIG // End signature block
