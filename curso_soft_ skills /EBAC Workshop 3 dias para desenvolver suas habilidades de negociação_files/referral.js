// When this file is modified the corresponding minified file should be
// regenerated with https://www.npmjs.com/package/uglify-js or a similar tool
// For example: uglifyjs Referral.v5.js > Referral.v5.min.js

if (isRRJSScriptLoaded === undefined) {
    var isRRJSScriptLoaded = false;
}

var executeIfRRScriptNotLoaded = function (callBack, param) {
    if (!isRRJSScriptLoaded) {
        if (param !== undefined) {
            callBack(param);
        }
        else {
            callBack();
        }
    }
}

var referralJS =
    (function () {


        // *********************************************************************************************************************************
        // Below functions are utility functions used throughout the app

        var rrSharedSpaceInternal =
            (function () {
                return {

                    // Check if the event that could add a member has executed in the last 3 seconds
                    shouldExecuteEvent: (eventName) => {
                        var lastRRWebSet = localStorage.getItem(eventName);
                        // See if last event was over 3 seconds ago
                        return !lastRRWebSet || (Date.now() - lastRRWebSet) > 3000;
                    },

                    rfc3986EncodeURIComponent: (str) => { //encodeURI doesn't replace all characters per RFC 38
                        return encodeURIComponent(str).replace(/[!'()*]/g, escape);
                    },

                    getScriptElement: () => {
                        return document.getElementById("RR_DIVID_V5");
                    },

                    //Returns host from self script 
                    getParser: () => {
                        var fullJsUrl = window.referralJS.rrSharedSpace.getScriptElement().getAttribute("src");
                        var parser = document.createElement('a');
                        parser.href = fullJsUrl;

                        return parser;
                    },

                    getQueryParameter: (name) => {
                        name = name.replace(/[\[]/, "\\\[").replace(/[\]]/, "\\\]");
                        var regexS = "[\\?&]" + name + "=([^&#]*)";
                        var regex = new RegExp(regexS);
                        var results = regex.exec(window.location.href);
                        if (results === null)
                            return null;
                        else
                            return decodeURIComponent(results[1]);
                    },

                    getCookie: (name) => {
                        var value = "; " + document.cookie;
                        var parts = value.split("; " + name + "=");
                        if (parts.length === 2) return parts.pop().split(";").shift();
                    },

                    setCookie: (cname, cvalue, exdays) => {
                        var d = new Date();
                        d.setTime(d.getTime() + exdays * 24 * 60 * 60 * 1000);
                        var expires = "expires=" + d.toGMTString();

                        // Attempt to set the cookie until it's successfully set, in order to remove the subdomain without an exhaustive TLD list
                        var domain = window.location.hostname;
                        var domainParts = domain.split('.');

                        for (var i = 1; i < (domainParts.length) && document.cookie.indexOf(cname + "=" + cvalue) == -1; i++) {
                            domain = domainParts.slice(-1 - (i)).join('.');
                            document.cookie = cname + "=" + cvalue + ";domain=." + domain + ";" + expires + ";path=/; SameSite=None; Secure";
                        }
                    },

                    //returns URL w/ parameters
                    buildURL: (parameters, basehost, route, debug, scriptV) => {
                        var url = "//" + basehost + "/" + route + "/?";

                        var decodeHtmlEntity = function (str) {
                            return str.replace(/&#(\d+);/g, function (match, dec) {
                                return String.fromCharCode(dec);
                            });
                        };
                        //Build w/ Parameters
                        if (typeof parameters !== "undefined") {
                            if (parameters !== null) {
                                for (var key in parameters) {
                                    // skip loop if the property is from prototype
                                    if (!parameters.hasOwnProperty(key)) continue;

                                    var value = parameters[key];

                                    //only write if has value
                                    if (value !== "" && value !== null) {

                                        switch (key.toLowerCase()) {
                                            case "email":
                                                //No decode of email... as it removes our fav plus. 
                                                url += key + "=" + window.referralJS.rrSharedSpace.rfc3986EncodeURIComponent(value) + "&";
                                                break;
                                            case "amount": {
                                                if (isNaN(value)) {
                                                    //It is not a number i.e. a string
                                                    //strip out any sign that is not a number as the first character
                                                    var amountTest = decodeHtmlEntity(value);
                                                    if (!amountTest.substring(0, 1).match(/^[0-9]+$/)) {
                                                        amountTest = amountTest.substring(1);
                                                    }

                                                    url += key + "=" + window.referralJS.rrSharedSpace.rfc3986EncodeURIComponent(amountTest) + "&";
                                                }
                                                else {
                                                    //it is a number so just encode and add to param
                                                    url += key + "=" + window.referralJS.rrSharedSpace.rfc3986EncodeURIComponent(value) + "&";
                                                }
                                                break;
                                            }
                                            case "customfields": { // Fit the nicer looker JSON into the existing architecture
                                                let custsomFieldCounter = 1;
                                                for (const property in parameters["customFields"].customText) {
                                                    url += "customtext" + custsomFieldCounter + "name=" + window.referralJS.rrSharedSpace.rfc3986EncodeURIComponent(`${property}`) + "&";
                                                    url += "customtext" + custsomFieldCounter + "value=" + window.referralJS.rrSharedSpace.rfc3986EncodeURIComponent(`${parameters["customFields"].customText[property]}`) + "&";
                                                    custsomFieldCounter++;
                                                }

                                                custsomFieldCounter = 1;
                                                for (const property in parameters["customFields"].customOption) {
                                                    url += "customoption" + custsomFieldCounter + "name=" + window.referralJS.rrSharedSpace.rfc3986EncodeURIComponent(`${property}`) + "&";
                                                    url += "customoption" + custsomFieldCounter + "value=" + window.referralJS.rrSharedSpace.rfc3986EncodeURIComponent(`${parameters["customFields"].customOption[property]}`) + "&";
                                                    custsomFieldCounter++;
                                                }
                                                break;
                                            }
                                            case "customtext1name":
                                            case "customtext1value":
                                            case "customtext2name":
                                            case "customtext2value": {
                                                if (parameters["customFields"]?.customText === undefined) { // only use these if someone hasn't updated their script to used the nested objects
                                                    var entityValue = decodeHtmlEntity(value);
                                                    url += key + "=" + window.referralJS.rrSharedSpace.rfc3986EncodeURIComponent(entityValue) + "&";
                                                }
                                                break;
                                            }
                                            case "customoption1name":
                                            case "customoption1value": {
                                                if (parameters["customFields"]?.customOption === undefined) { // only use these if someone hasn't updated their script to used the nested objects
                                                    var entityValue = decodeHtmlEntity(value);
                                                    url += key + "=" + window.referralJS.rrSharedSpace.rfc3986EncodeURIComponent(entityValue) + "&";
                                                }
                                                break;
                                            }
                                            default: {
                                                // All else decode HTML Entities
                                                var entityValue = decodeHtmlEntity(value);
                                                url += key + "=" + window.referralJS.rrSharedSpace.rfc3986EncodeURIComponent(entityValue) + "&";

                                                break;
                                            }
                                        }
                                    }
                                }
                            }
                        }

                        // Add client transaction key to url
                        var transactionKey = window.referralJS.rrSharedSpace.getScriptElement().getAttribute("transactionKey") !== null ? window.referralJS.rrSharedSpace.getScriptElement().getAttribute("transactionKey") : null;
                        url += "transactionKey=" + window.referralJS.rrSharedSpace.rfc3986EncodeURIComponent(transactionKey);

                        url += "&scriptv=" + window.referralJS.rrSharedSpace.rfc3986EncodeURIComponent(scriptV);

                        if (typeof window.location.href !== "undefined") {
                            if (window.location.href !== null) {

                                // Exclude sourceURL if greater than 1000 character 
                                if (window.location.href.length < 1000) {
                                    url += "&sourceURL=" + window.referralJS.rrSharedSpace.rfc3986EncodeURIComponent(window.location.href);
                                }

                                // Attach query strings from source URL if no rrverify param exists (except RR_WCID)
                                if (window.referralJS.rrSharedSpace.getQueryParameter("rrverify") !== "1") {
                                    url += location.search.replace(/[\?&]RR_WCID=[^&]+/, '').replace(/^&/, '?').replace("?", "&");
                                }

                            }
                        }

                        //Add debug
                        if (debug === "true") {
                            url += "&debug=true";
                        }

                        return url;
                    },

                    trackPageView: () => {

                        // Check for first party cookie
                        var currentTrack = window.referralJS.rrSharedSpace.getCookie("RR_WCID");

                        // No cookie... check query string
                        if (currentTrack === undefined) {
                            currentTrack = window.referralJS.rrSharedSpace.getQueryParameter("RR_WCID");
                        }

                        // If RR_WCID found or rrverify=1 lets trigger an externaltrack event
                        if (typeof currentTrack !== 'undefined' || window.referralJS.rrSharedSpace.getQueryParameter("rrverify") === "1") {

                            var trackParams = {
                                RR_WCID: currentTrack,
                                pageTitle: document.title.replace(/[^\w\s]+/g, ' '), // Replace groups of problematic characters with a single space
                                rrverify: window.referralJS.rrSharedSpace.getQueryParameter("rrverify")
                            };

                            var parser = window.referralJS.rrSharedSpace.getParser();
                            var basehost = parser.host;
                            var scriptV = parser.href;

                            var route = "externaltrack";
                            var url = window.referralJS.rrSharedSpace.buildURL(trackParams, basehost, route, false, scriptV);

                            // Post pixel
                            var div = document.createElement('div');
                            div.setAttribute("id", "rrTrackPage");

                            div.innerHTML = "<iframe src='" + url + "' scrolling='no' frameborder='0' width='1' height='1'></iframe>";
                            div.style.display = "none";

                            document.body.appendChild(div);
                        }
                    },

                    setUpEventListeners: () => {
                        window.addEventListener('message', function (e) {
                            if (e.data === "rrWebCallbackLoaded") {
                                if (typeof (window.referralJS.rrSharedSpace.onConversion) === 'function') {
                                    window.referralJS.rrSharedSpace.onConversion();
                                    window.referralJS.rrSharedSpace.onConversion = null;
                                }
                            }
                        });
                    },

                    makeGetRequest: async (destinationURI, callback) => {
                        var xmlHttp = new XMLHttpRequest();
                        xmlHttp.onreadystatechange = function () {
                            if (xmlHttp.readyState == 4 && xmlHttp.status == 200)
                                callback(xmlHttp.responseText);
                        }
                        xmlHttp.open("GET", destinationURI, true); // true for asynchronous
                        xmlHttp.send(null);
                    },

                    // Add the protocol used for the script when missing to the URI
                    getAbsolutePathFromParser: (parser) => {
                        var validPath = new RegExp('^(?:[a-z+]+:)?//', 'i');
                        if (validPath.test(parser.host)) {
                            return path;
                        }
                        else {
                            return parser.protocol + "//" + parser.host;
                        }
                    },

                    // Check for first party cookie, if none is found call webset to help convert the thrid party to convert, or create a new one if none found. Should run after safeStoreFirstPartyTracking
                    generateRRWCID: async (membercode) => {
                        if (window.referralJS.rrSharedSpace.getCookie("RR_WCID") === undefined) {
                            var parser = window.referralJS.rrSharedSpace.getParser();
                            var hostName = window.referralJS.rrSharedSpace.getAbsolutePathFromParser(parser);
                            await window.referralJS.rrSharedSpace.makeGetRequest(hostName + "/webset/" + membercode, function (cookieString) {
                                var cookieObject = JSON.parse(cookieString);
                                window.referralJS.rrSharedSpace.setCookie("RR_WCID", cookieObject.Value, cookieObject.Days);
                            });
                        }
                    },

                    //Store first party cookie
                    safeStoreFirstPartyTracking: () => {
                        var queryParamRR_WCID = window.referralJS.rrSharedSpace.getQueryParameter("RR_WCID");

                        //If we have a tracking param then let's look to write
                        if (queryParamRR_WCID != null) {

                            //Check for first party cookie
                            var currentTrack = window.referralJS.rrSharedSpace.getCookie("RR_WCID");

                            //Look for TTL (days cookie expiration)
                            var queryParamRR_WCID_TTL = window.referralJS.rrSharedSpace.getQueryParameter("RR_WCID_TTL");
                            if (queryParamRR_WCID_TTL != null) {
                                // check if queryParamRR_WCID_TTL value is an integer
                                // old method, not IE safe --> if (!Number.isInteger(queryParamRR_WCID_TTL)) {
                                if (!(!isNaN(parseFloat(queryParamRR_WCID_TTL)) && !isNaN(queryParamRR_WCID_TTL - 0))) {
                                    //not integer set to 13 months
                                    queryParamRR_WCIDTTL = 395;
                                }
                            } else {
                                //use default  13 months if no TTL days are set
                                queryParamRR_WCIDTTL = 395;
                            }

                            //Look for Attribution Model (first seen or last seen)
                            var queryParamRR_WCID_ATRIB = window.referralJS.rrSharedSpace.getQueryParameter("RR_WCID_ATRIB");
                            if (queryParamRR_WCID_ATRIB != null) {
                                if ((queryParamRR_WCID_ATRIB === "first") &&
                                    (typeof currentTrack != 'undefined')) {
                                    //First seen attrib and already has a first party tracking cookie... refresh/extend cookie
                                    window.referralJS.rrSharedSpace.setCookie("RR_WCID", currentTrack, queryParamRR_WCID_TTL);
                                } else {
                                    window.referralJS.rrSharedSpace.setCookie("RR_WCID", queryParamRR_WCID, queryParamRR_WCID_TTL);
                                }
                            } else {
                                //write cookie if no attribution mode
                                window.referralJS.rrSharedSpace.setCookie("RR_WCID", queryParamRR_WCID, queryParamRR_WCID_TTL);
                            }
                        }
                        else if (window.referralJS.rrSharedSpace.getQueryParameter("via-rr")) { // There is no first party cookie, but there is a membercode
                            window.referralJS.rrSharedSpace.generateRRWCID(window.referralJS.rrSharedSpace.getQueryParameter("via-rr"));
                        }
                    }
                }
            });

        var rrEventSpaceInternal =
            (function () {
                var o = {};

                (function () {
                    GenericWidget = {}
                }).call(this), GenericWidget.domReady = function (e) {
                    var t = !1,
                        n = function () {
                            document.addEventListener ? (document.removeEventListener("DOMContentLoaded", o), window.removeEventListener("load", o)) : (document.detachEvent("onreadystatechange", o), window.detachEvent("onload", o))
                        },
                        o = function () {
                            t || !document.addEventListener && "load" !== event.type && "complete" !== document.readyState || (t = !0, n(), e())
                        };
                    if ("complete" === document.readyState) e();
                    else if (document.addEventListener) document.addEventListener("DOMContentLoaded", o), window.addEventListener("load", o);
                    else {
                        document.attachEvent("onreadystatechange", o), window.attachEvent("onload", o);
                        var i = !1;
                        try {
                            i = null === window.frameElement && document.documentElement
                        } catch (l) { }
                        i && i.doScroll && ! function r() {
                            if (!t) {
                                try {
                                    i.doScroll("left")
                                } catch (o) {
                                    return setTimeout(r, 50)
                                }
                                t = !0, n(), e()
                            }
                        }()
                    }
                },
                    function () {
                        GenericWidget.initInlineWidgets = function () {
                            return GenericWidget.domReady(function () {
                                return GenericWidget.createInlineWidgets()
                            })
                        }, GenericWidget.initBadgeWidget = function (e) {
                            return GenericWidget.domReady(function () {
                                return GenericWidget.createBadgeWidget(e)
                            })
                        }, GenericWidget.createInlineWidgets = function () {
                            var e, t, n, o, i;
                            for (t = document.querySelectorAll(".generic-widget-inline-widget"), i = [], n = 0, o = t.length; o > n; n++) e = t[n], e.getAttribute("data-processed") ? i.push(void 0) : (e.setAttribute("data-processed", !0), i.push(new GenericWidget.Iframe(e, !0)));
                            return i
                        }, GenericWidget.createBadgeWidget = function (e) {
                            return this.destroyBadgeWiget(), GenericWidget.badgeWidget = new GenericWidget.BadgeWidget({
                                color: e.color,
                                text: e.text,
                                branding: e.branding,
                                position: e.position,
                                onClick: function () {
                                    return GenericWidget.showPopupWidget(e.url)
                                }
                            })
                        }, GenericWidget.destroyBadgeWiget = function () {
                            return GenericWidget.badgeWidget ? (GenericWidget.badgeWidget.destroy(), delete GenericWidget.badgeWidget) : void 0
                        }, GenericWidget.showPopupWidget = function (e) {
                            return this.closePopupWidget(), GenericWidget.popupWidget = new GenericWidget.PopupWidget(e, function () {
                                return delete GenericWidget.popupWidget
                            }), GenericWidget.popupWidget.show()
                        }, GenericWidget.closePopupWidget = function () {
                            return GenericWidget.popupWidget ? GenericWidget.popupWidget.close() : void 0
                        }
                    }.call(this),
                    function () {
                        GenericWidget.Iframe = function () {
                            function e(e, t) {
                                this.parent = e, this.inlineStyles = null !== t ? t : !1, this.build(), this.inject()
                            }
                            return e.prototype.isMobile = /Android|webOS|iPhone|iPad|iPod|BlackBerry|IEMobile|Opera Mini/i.test(navigator.userAgent), e.prototype.build = function () {
                                return this.node = document.createElement("iframe"), this.node.src = this.getSource(), this.node.width = "100%", this.node.height = "100%", this.node.frameBorder = "0"
                            }, e.prototype.inject = function () {
                                return this.format(), this.parent.appendChild(this.buildSpinner()), this.parent.appendChild(this.node)
                            }, e.prototype.getSource = function () {
                                return this.parent.getAttribute("data-url")
                            }, e.prototype.format = function () {
                                return this.isMobile ? this.formatMobile() : this.formatDesktop()
                            }, e.prototype.formatDesktop = function () {
                                return this.inlineStyles ? this.parent.setAttribute("style", "position: relative;" + this.parent.getAttribute("style")) : void 0
                            }, e.prototype.formatMobile = function () {
                                return this.inlineStyles ? this.parent.setAttribute("style", "position: relative;overflow-y:auto;-webkit-overflow-scrolling:touch;" + this.parent.getAttribute("style")) : this.parent.className += " mobile"
                            }, e.prototype.buildSpinner = function () {
                                var e;
                                return e = document.createElement("div"), e.className = "spinner", e.appendChild(this.buildBounce(1)), e.appendChild(this.buildBounce(2)), e.appendChild(this.buildBounce(3)), e
                            }, e.prototype.buildBounce = function (e) {
                                var t;
                                return t = document.createElement("div"), t.className = "bounce" + e, t
                            }, e
                        }()
                    }.call(this),
                    function () {
                        var e = function (e, t) {
                            return function () {
                                return e.apply(t, arguments)
                            }
                        };
                        GenericWidget.PopupWidget = function () {
                            function t(t, n) {
                                this.url = t, this.onClose = n, this.close = e(this.close, this), this.pageRoot = document.getElementsByTagName("html")[0]
                            }
                            return t.prototype.show = function () {
                                return this.buildOverlay(), this.insertOverlay(), this.lockPageScroll()
                            }, t.prototype.close = function () {
                                return this.destroyOverlay(), this.onClose(), this.unlockPageScroll()
                            }, t.prototype.buildOverlay = function () {
                                return this.overlay = document.createElement("div"), this.overlay.className = "generic-widget-overlay", this.overlay.appendChild(this.buildCloseOverlay()), this.overlay.appendChild(this.buildPopup())
                            }, t.prototype.insertOverlay = function () {
                                return document.body.insertBefore(this.overlay, document.body.firstChild)
                            }, t.prototype.buildCloseOverlay = function () {
                                var e;
                                return e = document.createElement("div"), e.className = "generic-widget-close-overlay", e.onclick = this.close, e
                            }, t.prototype.buildPopup = function () {
                                var e;
                                return e = document.createElement("div"), e.className = "generic-widget-popup", e.appendChild(this.buildPopupContent()), e.appendChild(this.buildCloseButton()), e
                            }, t.prototype.buildPopupContent = function () {
                                var e;
                                return e = document.createElement("div"), e.className = "generic-widget-popup-content", e.setAttribute("data-url", this.url), new GenericWidget.Iframe(e), e
                            }, t.prototype.buildCloseButton = function () {
                                var e;
                                return e = document.createElement("div"), e.className = "generic-widget-popup-close", e.onclick = this.close, e
                            }, t.prototype.destroyOverlay = function () {
                                return this.overlay.parentNode.removeChild(this.overlay)
                            }, t.prototype.lockPageScroll = function () {
                                return this.pageRoot.className += " generic-widget-page-scroll-locked"
                            }, t.prototype.unlockPageScroll = function () {
                                return this.pageRoot.className = this.pageRoot.className.replace(" generic-widget-page-scroll-locked", "")
                            }, t
                        }()
                    }.call(this),
                    function () {
                        GenericWidget.BadgeWidget = function () {
                            function e(e) {
                                this.options = e, this.buildWidget(), this.insertWidget()
                            }
                            return e.prototype.destroy = function () {
                                return this.widget.parentNode.removeChild(this.widget)
                            }, e.prototype.buildWidget = function () {
                                if (this.options.position === "alternate") {
                                    return this.widget = document.createElement("div"), this.widget.className = "generic-widget-badge-widget", this.widget.appendChild(this.buildContent())
                                }
                                else {
                                    return this.widget = document.createElement("div"), this.widget.className = "generic-widget-badge-widget rotate", this.widget.appendChild(this.buildContent())
                                }
                            }, e.prototype.insertWidget = function () {
                                return document.body.insertBefore(this.widget, document.body.firstChild)
                            }, e.prototype.buildContent = function () {
                                var e;
                                return e = document.createElement("div"), e.className = "generic-widget-badge-content", "#ffffff" === this.options.color && (e.className += " white"), e.onclick = this.options.onClick, e.innerHTML = this.options.text, e.style.background = this.options.color, this.options.branding && e.appendChild(this.buildBranding()), e
                            }, e.prototype.buildBranding = function () {
                                var e;
                                return e = document.createElement("span"), e.innerHTML = "powered by Referral Rock", e
                            }, e
                        }()
                    }.call(this), GenericWidget.initInlineWidgets();


                // This order matters
                window.referralJS.rrSharedSpace.safeStoreFirstPartyTracking();

                // Only execute these scripts once regardless of how many times the script is loaded. 
                executeIfRRScriptNotLoaded(window.referralJS.rrSharedSpace.trackPageView);
                executeIfRRScriptNotLoaded(window.referralJS.rrSharedSpace.setUpEventListeners);

                checkForEvent();

                o.executeEvent = function (eventType, eventObject) {
                    //validate
                    if (typeof eventType === "undefined" || eventType === null) {
                        if (window.console) {
                            console.log("no event specified or empty");
                        }
                        return;
                    }

                    //validate
                    if (typeof eventObject === "undefined" || eventObject === null) {
                        if (window.console) {
                            console.log("no event object specified or empty");
                        }
                        return;
                    }

                    switch (eventType.toLowerCase()) {
                        case "access":
                            eventObject.mode = "access";
                            break;
                        case "conversion":
                            eventObject.mode = "conversion";
                            break;
                        case "referralupdate":
                            eventObject.mode = "referralupdate";
                            break;
                        case "widget":
                            eventObject.mode = "widget";
                            break;
                        default:
                            if (window.console) {
                                console.log(eventType.toLowerCase() + " is an invalid event type use access, conversion, referralupdate, or widget. ");
                            }
                            return;
                            break;
                    }
                    execute(eventObject);
                    if (window.console) {
                        console.log("Success");
                    }
                    return;
                }
                //Check mode of operation
                function checkForEvent() {

                    //Check for Access
                    if (typeof window.referralJS.access !== "undefined") {
                        if (window.referralJS.access !== null && window.referralJS.rrSharedSpace.shouldExecuteEvent('last_rr_webset')) {
                            {
                                //Assign correct mode
                                window.referralJS.access.mode = "access";
                                execute(window.referralJS.access);
                            }
                        }
                    }

                    //Check for Conversion
                    if (typeof window.referralJS.conversion !== "undefined") {
                        if (window.referralJS.conversion !== null && window.referralJS.rrSharedSpace.shouldExecuteEvent('last_rr_webset')) {
                            {
                                //Assign correct mode
                                window.referralJS.conversion.mode = "conversion";
                                execute(window.referralJS.conversion);
                            }
                        }
                    }

                    //Check for referralupdate
                    if (typeof window.referralJS.referralUpdate !== "undefined") {
                        if (window.referralJS.referralUpdate !== null) {
                            {
                                //Assign correct mode
                                window.referralJS.referralUpdate.mode = "referralupdate";
                                execute(window.referralJS.referralUpdate);
                            }
                        }
                    }

                    //check for widget
                    if (typeof window.referralJS.widget !== "undefined") {
                        if (window.referralJS.widget !== null && window.referralJS.rrSharedSpace.shouldExecuteEvent('last_rr_webset')) {
                            {
                                //Assign correct mode
                                window.referralJS.widget.mode = "widget";
                                execute(window.referralJS.widget);
                            }
                        }
                    }
                }

                o.checkForEvent = checkForEvent;

                function execute(eventVars) {

                    var mode;
                    var targetID;
                    var debug = "false";
                    var wposition;
                    var wcolor;
                    var wtext;

                    if (typeof eventVars !== "undefined") {
                        if (eventVars !== null) {
                            localStorage.setItem('last_rr_webset', Date.now());
                            mode = eventVars.mode;
                            debug = eventVars.debug;
                            targetid = eventVars.targetId;
                            wposition = eventVars.widgetPosition;
                            wcolor = eventVars.widgetColor;
                            wtext = eventVars.widgetText;
                            window.referralJS.rrSharedSpace.onConversion = eventVars.onConversion;

                            //Check if mode is valid

                            var route;  //URL Route
                            var action; //Action to take        

                            switch (mode) {
                                case "access":
                                    route = "access";
                                    action = "replacelink";
                                    break;
                                case "referralupdate":
                                    route = "referralupdate";
                                    action = "postpixelreferralupdate";
                                    break;
                                case "conversion":
                                    route = "webcallback";
                                    action = "postpixelconvert";

                                    //Check for first party cookie
                                    var currentTrack = window.referralJS.rrSharedSpace.getCookie("RR_WCID");

                                    if (typeof currentTrack != 'undefined') {
                                        if (!eventVars.parameters.hasOwnProperty("RR_WCID")) {
                                            //write out param 
                                            eventVars.parameters["RR_WCID"] = currentTrack;
                                        }

                                    }
                                    break;
                                case "widget":
                                    route = "access";
                                    action = "widget";
                                    break;
                                default:
                                    return;
                            }

                            //Get the URL
                            var parser = window.referralJS.rrSharedSpace.getParser();
                            var basehost = parser.host;
                            var scriptV = parser.href;
                            var url = window.referralJS.rrSharedSpace.buildURL(eventVars.parameters, basehost, route, debug, scriptV);

                            //take action
                            switch (action) {
                                case "postpixelconvert":
                                    // Post pixel
                                    var divConvert = document.createElement('div');
                                    divConvert.setAttribute("id", "rrPixelConvert-" + uuidv4());

                                    if (debug === "true") {
                                        divConvert.innerHTML = "<iframe src='" + url + "' scrolling='yes' frameborder='0' width='600' height='800'></iframe>";
                                    }
                                    else {
                                        //none debug... so hide it
                                        divConvert.innerHTML = "<iframe src='" + url + "' scrolling='no' frameborder='0' width='1' height='1'></iframe>";
                                        divConvert.style.display = "none";
                                    }
                                    document.body.appendChild(divConvert);

                                    break;
                                case "postpixelreferralupdate":
                                    // Post pixel
                                    var divUpdate = document.createElement('div');
                                    divUpdate.setAttribute("id", "rrPixelUpdate-" + uuidv4());

                                    if (debug === "true") {
                                        divUpdate.innerHTML = "<iframe src='" + url + "' scrolling='yes' frameborder='0' width='600' height='800'></iframe>";
                                    }
                                    else {
                                        //none debug... so hide it
                                        divUpdate.innerHTML = "<iframe src='" + url + "' scrolling='no' frameborder='0' width='1' height='1'></iframe>";
                                        divUpdate.style.display = "none";
                                    }
                                    document.body.appendChild(divUpdate);

                                    break;
                                //Used for Access... to replace the link.
                                case "replacelink":
                                    var targetElement = document.getElementById(targetid);

                                    if (targetElement) {
                                        if (targetElement.nodeName === 'A') {
                                            targetElement.setAttribute("href", url);
                                        }
                                        else if (targetElement.nodeName === 'IFRAME') {
                                            targetElement.setAttribute("src", url);
                                        }
                                        else if (targetElement.nodeName === 'INPUT') {
                                            targetElement.setAttribute("onclick", "window.location='" + url + "'");
                                        }
                                    }

                                    // Post Debug Iframe
                                    if (debug === "true") {
                                        var div2 = document.createElement('div');
                                        div2.setAttribute("id", "rrPixel");
                                        div2.innerHTML = "<iframe src='" + url + "' scrolling='yes' frameborder='0' width='600' height='800'></iframe>";

                                        document.body.appendChild(div2);
                                    }

                                    break;
                                case "widget":
                                    dynamicallyLoadCSS("//" + basehost + "/webpixel/beta/css/genericwidget.css");
                                    url = url + "&view=iframe";
                                    GenericWidget.initBadgeWidget({ url: url, text: wtext, color: wcolor, branding: false, position: wposition });

                                    break;
                                default:
                                    return;
                            }
                        }
                    }
                }

                function dynamicallyLoadCSS(url) {
                    var cssFile = document.createElement("link"); // Make a script DOM node
                    cssFile.href = url;
                    cssFile.rel = "stylesheet";
                    document.head.appendChild(cssFile); // Add it to the end of the head section of the page (could change 'head' to 'body' to add it to the end of the body section instead)
                }

                function uuidv4() {
                    return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function (c) {
                        var r = Math.random() * 16 | 0, v = c == 'x' ? r : (r & 0x3 | 0x8);
                        return v.toString(16);
                    });
                }

                return o;

            })

        var rrScriptCheckerSpaceInternal = (function () {
            if (window.referralJS.rrSharedSpace.getQueryParameter("rrverify") === "1") { // If customer is verifying script installation pull the success modal.
                var sdkPath = window.referralJS.rrSharedSpace.getParser().host;
                if (sdkPath != null && sdkPath.length > 0) {
                    window.referralJS.rrSharedSpace.makeGetRequest("//" + sdkPath + "/sdk/SuccessModal.html", function (responseHtml) {
                        var successModal = document.createElement('div');
                        successModal.innerHTML = responseHtml;
                        document.body.appendChild(successModal);
                    });
                }
            }

        });

        return {
            // These backing fields are used to add listeners to the setting objects so they can be added before or after the JS script is loaded. 
            settingsConversion: (typeof window.referralJS?.conversion !== 'undefined') ? window.referralJS.conversion : null,
            set conversion(initValues) {
                this.settingsConversion = initValues;
                window.referralJS.rrEventSpace.checkForEvent();
            },
            get conversion() {
                return this.settingsConversion;
            },
            settingsWidget: (typeof window.referralJS?.widget !== 'undefined') ? window.referralJS.widget : null,
            set widget(initValues) {
                this.settingsWidget = initValues;
                window.referralJS.rrEventSpace.checkForEvent();
            },
            get widget() {
                return this.settingsWidget;
            },
            settingsReferralUpdate: (typeof window.referralJS?.referralUpdate !== 'undefined') ? window.referralJS.referralUpdate : null,
            set referralUpdate(initValues) {
                this.settingsReferralUpdate = initValues;
                window.referralJS.rrEventSpace.checkForEvent();
            },
            get referralUpdate() {
                return this.settingsReferralUpdate;
            },
            settingsAccess: (typeof window.referralJS?.access !== 'undefined') ? window.referralJS.access : null,
            set access(initValues) {
                this.settingsAccess = initValues;
                window.referralJS.rrEventSpace.checkForEvent();
            },
            get access() {
                return this.settingsAccess;
            },
            rrSharedSpace: rrSharedSpaceInternal,
            rrEventSpace: rrEventSpaceInternal,
            rrScriptCheckerSpace: rrScriptCheckerSpaceInternal
        }
    })();

// Inializing these after the return makes it so the spaces are only visible under referralJS
window.referralJS.rrSharedSpace = window.referralJS.rrSharedSpace();
window.referralJS.rrEventSpace = window.referralJS.rrEventSpace();
window.referralJS.rrScriptCheckerSpace = window.referralJS.rrScriptCheckerSpace();

window.addEventListener('load', function () {
    isRRJSScriptLoaded = true;
});
