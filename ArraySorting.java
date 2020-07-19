int numberToSort = 100;
Rectangle[] rects = new Rectangle[numberToSort];
int maxRectHeight;
int pass = 0;

// The statements in the setup() function 
// execute once when the program begins
void setup() {
  //size(640, 360);  // Size must be the first statement
  size(1280, 720);
  stroke(255);     // Set line drawing color to white
  frameRate(10);

  float rectWidth = width / (float) rects.length;
  for (int i = 0; i < rects.length; i++) {
    rects[i] = new Rectangle(rectWidth, i+1);
  }

  //get the max value of the array.
  //this is only really necessary if i randomize the values
  maxRectHeight = rects[0].getHeight();
  for (int i = 0; i < rects.length; i++) {
    if (rects[i].getHeight() > maxRectHeight) {
      maxRectHeight = rects[i].getHeight();
    }
  }
  
  //shuffle the array
  shuffleArray(rects);
}
// The statements in draw() are executed until the 
// program is stopped. Each statement is executed in 
// sequence and after the last line is read, the first 
// line is executed again.
void draw() { 
  background(0);   // Clear the screen with a black background
  for (int i = 0; i < rects.length; i++) {
    rects[i].display(i);
  }
  if (pass < rects.length - 1) {
    selectionSortPass(pass);
    pass++;
  } else {
    //randomize the array
    shuffleArray(rects);
    pass = 0;
  }
}

void selectionSortPass(int i) {
  // Find the minimum element in unsorted array 
  int min_idx = i; 
  for (int j = i+1; j < rects.length; j++) 
    if (rects[j].getHeight() < rects[min_idx].getHeight()) 
      min_idx = j; 

  // Swap the found minimum element with the first 
  // element       
  Rectangle temp = rects[min_idx];
  rects[min_idx] = rects[i];
  rects[i] = temp;
  
  rects[min_idx].setChanging();
  rects[i].setChanging();
}

class Rectangle {
  float rect_width;
  int rect_height; //this is the same as the rectangles "value"
  boolean changing = false;

  // Contructor
  Rectangle(float rect_width, int rect_height) {
    this.rect_width = rect_width;
    this.rect_height = rect_height;
  }

  int getHeight() {
    return rect_height;
  }
  
  void setChanging() {
    this.changing = true; 
  }

  // Custom method for drawing the object
  void display(int curr_index) {
    if(changing) {
      fill(color(255, 100, 10));
      changing = false;
    } else {
      fill(102);
    }
    //rect(x,y,width,height);
    float ratio = ((float) rect_height / (float) maxRectHeight);
    float rect_display_height = ratio * height;
    rect(rect_width * curr_index, (1-ratio) * height, rect_width, rect_display_height);
  }
}

void shuffleArray(Rectangle[] a) {
  int n = a.length;
  for (int i = 0; i < n; i++) {
    int change = i + (int) random(n - i);
    swap(a, i, change);
  }
}

void swap(Rectangle[] a, int i, int change) {
  Rectangle helper = a[i];
  a[i] = a[change];
  a[change] = helper;
}
