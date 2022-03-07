export interface food{
  name: string
  rating: number
  block: string
  streetName: string
  floorNumber: string
  unitNumber: string
  postalCode: number
  latitude: number
  longitude: number
  uuid: string
  introduction: string
  reviews: string[]
}

export interface feedback{
  username: string
  email: string
  contactNumber: number
  comment: string
}

export interface user{
  username: string
  password: string
  email: string
  contactNumber: number
}

export interface userlogin{
  username: string
  password: string
}

export interface favourite{
  username: string
  userId: number
  stallName: string
  uuid: string
}

