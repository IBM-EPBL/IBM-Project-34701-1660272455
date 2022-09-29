from flask import Flask, escape, request

app = Flask(__name__)

app.config['SECRET_KEY'] = 'any secret key'

@app.route('/')
def hello():
    return "Hello World!"


from flask_wtf import FlaskForm
from wtforms import SubmitField, HiddenField, StringField, IntegerField, DecimalField
from wtforms.validators import Email

class EmployeeForm(FlaskForm):
    id = HiddenField()
    name = StringField('Name')
    email = StringField('Email', validators=[Email()])
    salary = DecimalField('Salary')
    submit = SubmitField("Save")


from flask_sqlalchemy import SQLAlchemy
app.config["SQLALCHEMY_DATABASE_URI"] = "sqlite:////tmp/employee.db"
db = SQLAlchemy(app)

class Employee(db.Model):
    id = db.Column(db.Integer, primary_key=True)
    name = db.Column(db.String)
    email = db.Column(db.String)
    salary = db.Column(db.Numeric)
    references = db.Column(db.String)

    def __repr__(self):
        return "(%r, %r, %r)" %(self.name,self.email,self.salary)


from flask import render_template, request, flash, redirect, url_for

@app.route("/employee", methods=["GET", "POST"])
def createEmployee():
    form = EmployeeForm(request.form)
    employees = Employee.query.all()
    if form.validate_on_submit():
        employee = Employee(name=form.name.data, email=form.email.data, salary=form.salary.data)
        db.session.add(employee)
        db.session.commit()
        db.session.refresh(employee)
        db.session.commit()
        flash("Added Employee Successfully")
        return redirect(url_for("createEmployee"))
    return render_template("employee.html", title="Employee", form=form, employees=employees)

@app.route("/updateEmployee/<int:employee_id>", methods=["GET", "POST"])
def updateEmployee(employee_id):
    employee = Employee.query.get(employee_id)
    form = EmployeeForm(request.form, obj=employee)
    if form.validate_on_submit():
        form.populate_obj(employee)
        db.session.commit()
        flash("Updated Employee Successfully")
        return redirect(url_for("createEmployee"))
    return render_template("employee.html", title="Employee", form=form, employees=Employee.query.all())

@app.route("/deleteEmployee/<int:employee_id>", methods=["GET", "POST"])
def deleteEmployee(employee_id):
    employee = Employee.query.get(employee_id)
    db.session.delete(employee)
    db.session.commit()
    return redirect(url_for("createEmployee"))
