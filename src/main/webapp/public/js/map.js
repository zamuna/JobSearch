/**
 * Created by Zamuna on 5/23/2017.
 */

function initGMap(zipCode, mapId) {
    var map;
    var geocoder = new google.maps.Geocoder();
    geocoder.geocode({address: zipCode}, function (results, status) {
        if (status == google.maps.GeocoderStatus.OK) {
            lat = results[0].geometry.location.lat();
            lon = results[0].geometry.location.lng();
            var mapOptions = {
                zoom: 8,
                center: new google.maps.LatLng(lat, lon)
            };
            map = new google.maps.Map(document.getElementById(mapId),
                mapOptions);
            var marker = new google.maps.Marker({
                position: {lat: lat, lng: lon},
                map: map,
                visible:true
            });
            marker.setMap(map);
            // Add interaction listeners to make weather requests
            google.maps.event.addListener(map, 'idle', checkIfDataRequested);

        } else {
            alert("Geocode was not successful for the following reason: " + status);
        }
    console.log("showMap");});
}

function initMap(zipCode,mapId)
{
    var map;
    var geoJSON;
    var request;
    var gettingData = false;
    var openWeatherMapKey = "6c974d3d4c6fd517225aff5aec044a85";
    //var openWeatherMapKey = '09fd7ca56efbca1da537b805e462c5a4';
    var lat;
    var lon;
    console.log(zipCode);
    console.log(mapId);
    initializeMap(zipCode,mapId);

    function initializeMap(zipCode, mapId) {
        var geocoder = new google.maps.Geocoder();
        geocoder.geocode({address: zipCode}, function (results, status) {
            if (status == google.maps.GeocoderStatus.OK) {
                lat = results[0].geometry.location.lat();
                lon = results[0].geometry.location.lng();
                var mapOptions = {
                    zoom: 8,
                    center: new google.maps.LatLng(lat, lon)
                };
                map = new google.maps.Map(document.getElementById(mapId),
                    mapOptions);
                var marker = new google.maps.Marker({
                    position: {lat: lat, lng: lon},
                    map: map,
                    visible:true
                });
                marker.setMap(map);
                // Add interaction listeners to make weather requests
                google.maps.event.addListener(map, 'idle', checkIfDataRequested);
                // Sets up and populates the info window with details
                map.data.addListener('click', function (event) {
                    infowindow.setContent(
                        "<img src=" + event.feature.getProperty("icon") + ">"
                        + "<br /><strong>" + event.feature.getProperty("city") + "</strong>"
                        + "<br />" + event.feature.getProperty("temperature") + "&deg;C"
                        + "<br />" + event.feature.getProperty("weather")
                    );
                    infowindow.setOptions({
                        position: {
                            lat: event.latLng.lat(),
                            lng: event.latLng.lng()
                        },
                        pixelOffset: {
                            width: 0,
                            height: -15
                        }
                    });
                    infowindow.open(map);
                });
            } else {
                alert("Geocode was not successful for the following reason: " + status);
            }
        });
    }

//
    var checkIfDataRequested = function () {
        // Stop extra requests being sent
        while (gettingData === true) {
            request.abort();
            gettingData = false;
        }
        getCoords();
    };
// Get the coordinates from the Map bounds
    var getCoords = function () {
        var bounds = map.getBounds();
        var NE = bounds.getNorthEast();
        var SW = bounds.getSouthWest();
        getWeather(NE.lat(), NE.lng(), SW.lat(), SW.lng());
    };
// Make the weather request
    var getWeather = function (northLat, eastLng, southLat, westLng) {
        gettingData = true;
        var requestString = "http://api.openweathermap.org/data/2.5/box/city?bbox="
            + westLng + "," + northLat + "," //left top
            + eastLng + "," + southLat + "," //right bottom
            + map.getZoom()
            + "&cluster=yes&format=json"
            + "&APPID=" + openWeatherMapKey;
        console.log(requestString);
        request = new XMLHttpRequest();
        request.onload = proccessResults;
        request.open("get", requestString, true);
        request.send();
    };
// Take the JSON results and proccess them
    var proccessResults = function () {
        console.log("result XML:"+this.responseText);
        var results = JSON.parse(this.responseText);
        console.log("result:"+results);
        if (results.list.length > 0) {
            resetData();
            for (var i = 0; i < results.list.length; i++) {
                geoJSON.features.push(jsonToGeoJson(results.list[i]));
            }
            drawIcons();
        }
    };
    var infowindow = new google.maps.InfoWindow();
// For each result that comes back, convert the data to geoJSON
    var jsonToGeoJson = function (weatherItem) {
        var feature = {
            type: "Feature",
            properties: {
                city: weatherItem.name,
                weather: weatherItem.weather[0].main,
                temperature: weatherItem.main.temp,
                min: weatherItem.main.temp_min,
                max: weatherItem.main.temp_max,
                humidity: weatherItem.main.humidity,
                pressure: weatherItem.main.pressure,
                windSpeed: weatherItem.wind.speed,
                windDegrees: weatherItem.wind.deg,
                windGust: weatherItem.wind.gust,
                icon: "http://openweathermap.org/img/w/"
                + weatherItem.weather[0].icon + ".png",
                coordinates: [weatherItem.coord.Lon, weatherItem.coord.Lat]
            },
            geometry: {
                type: "Point",
                coordinates: [weatherItem.coord.Lon, weatherItem.coord.Lat]
            }
        };
        // Set the custom marker icon
        map.data.setStyle(function (feature) {
            return {
                icon: {
                    url: feature.getProperty('icon'),
                    anchor: new google.maps.Point(25, 25)
                }
            };
        });
        // returns object
        return feature;
    };
// Add the markers to the map
    var drawIcons = function () {
        map.data.addGeoJson(geoJSON);
        // Set the flag to finished
        gettingData = false;
    };
// Clear data layer and geoJSON
    var resetData = function () {
        geoJSON = {
            type: "FeatureCollection",
            features: []
        };
        map.data.forEach(function (feature) {
            map.data.remove(feature);
        });
    };
// google.maps.event.addDomListener(window, 'load', initialize);
}