import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { foodService } from '../food.service';
import { favourite, food } from '../model';

@Component({
  selector: 'app-food-detail',
  templateUrl: './food-detail.component.html',
  styleUrls: ['./food-detail.component.css']
})
export class FoodDetailComponent implements OnInit {

  uuid: string = ''
  food!: food;
  latitude!: number
  longitude!: number
  username!: string

  constructor(private activatedRoute:ActivatedRoute, private foodSvc:foodService,private router:Router) { }

  ngOnInit(): void {
    this.uuid = this.activatedRoute.snapshot.params['uuid']
    this.username = this.activatedRoute.snapshot.params['username']
    this.foodSvc.getFood(this.uuid).then(result =>{
      this.food = result
      const stringarray = result.reviews.toString().replace('\\',"|").replace('[',"").replace(']',"").split('","')
      this.food.reviews = stringarray;
      this.food.introduction = result.introduction.replace('<p>',"").replace('<br></p>','')})

    }

  goToMap(lat:number, long:number){
    console.info(lat)
    console.info(long)
    this.router.navigate(['/map',this.username, {long,lat}])
  }

  saveFood(food:food){

    console.log(food)

    const favourite: favourite  = {
      stallName: this.food.name,
      uuid: this.food.uuid,
      username: this.username,
      userId: 0
    }

    console.log(favourite)


    this.foodSvc.saveFood(favourite).then(result => {
      console.info(result)
    })
    }
  }



