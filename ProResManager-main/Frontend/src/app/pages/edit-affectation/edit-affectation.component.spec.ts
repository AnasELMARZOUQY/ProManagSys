import { ComponentFixture, TestBed } from '@angular/core/testing';
import { EditAffectationComponent } from './edit-affectation.component';
 
describe('EditAffectationComponent', () => {
  let component: EditAffectationComponent;
  let fixture: ComponentFixture<EditAffectationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EditAffectationComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EditAffectationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
