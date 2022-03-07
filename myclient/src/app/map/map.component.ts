import { createPlatform } from '@angular/core';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { GoogleMap } from 'googlemaps';

@Component({
  selector: 'app-map',
  templateUrl: './map.component.html',
  styleUrls: ['./map.component.css']
})
export class MapComponent implements OnInit {

  form!:FormGroup

  myArray!: string[];

  map!: google.maps.Map;
  center!: google.maps.LatLngLiteral;

  source!: google.maps.LatLngLiteral;
  destination!: google.maps.LatLngLiteral;

  options: google.maps.MapOptions = {
    mapTypeId: google.maps.MapTypeId.ROADMAP,
    scrollwheel:true,
    disableDefaultUI:true,
    disableDoubleClickZoom:true,
    zoom: 14
  }



  ds!: google.maps.DirectionsService;
  dr!:google.maps.DirectionsRenderer;
  stepDisplay!: google.maps.InfoWindow;

  lat!: number
  long!: number


  constructor(private activatedRoute:ActivatedRoute, private fb:FormBuilder) { }

  ngOnInit(): void {

    this.lat = parseFloat(this.activatedRoute.snapshot.params['lat'])
    this.long = parseFloat(this.activatedRoute.snapshot.params['long'])




    this.ds = new google.maps.DirectionsService();
    this.dr = new google.maps.DirectionsRenderer({
      map: null!,
      suppressMarkers:true,
    });


    navigator.geolocation.getCurrentPosition(position => {
      this.center = {
        lat: position.coords.latitude,
        lng: position.coords.longitude
      };

      this.source = {
        lat: position.coords.latitude,
        lng: position.coords.longitude
      }

      this.destination = {
        lat: this.lat,
        lng: this.long
      }

      this.map = new google.maps.Map(document.getElementById('map-canvas')!,
      {...this.options,
      center:this.source});

    const marker = new google.maps.Marker({
      position: this.source,
    /*   icon:{
      url: './assets/imgs/map-pin',
      anchor: new google.maps.Point(35,10),
      scaledSize: new google.maps.Size(50,50)}, */
      map: this.map
    });

    const destinationmarker = new google.maps.Marker({
      position: this.destination,
      map: this.map
    });

    this.setRoutePolyline();

    this.dr.setPanel(document.getElementById("floating-panel") as HTMLElement)

  })

}

setRoutePolyline(){
  let request = {
    origin: this.source,
    destination: this.destination,
    travelMode: google.maps.TravelMode.TRANSIT,
    optimizeWaypoints:true
  };

  this.ds.route(request,(response,status)=>{
    this.dr.setOptions({
      suppressPolylines:false,
      map: this.map
    })

    if (status == google.maps.DirectionsStatus.OK){
      this.dr.setDirections(response);
      this.showSteps(response);
    }
  })
}

 showSteps(directionResults:any){
  var myRoute = directionResults.routes[0].legs[0];
  for (var i = 0; i < myRoute.steps.length;i++){
    var marker = new google.maps.Marker({
      position:myRoute.steps[i].start_point,
      map:this.map
    });
    this.attachInstructionText(marker,myRoute.steps[i].instructions);
  }
}

 attachInstructionText(marker:any, text:string) {
  google.maps.event.addListener(marker, 'click', () => {
  let stepDisplay = new google.maps.InfoWindow({
    content: text
  })
  stepDisplay.open(this.map,marker)
  });
}


}


