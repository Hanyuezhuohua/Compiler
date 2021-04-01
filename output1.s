	.text
	.globl	g_init
	.p2align	1
	.type	g_init,@function
g_init:
.g_init_.0: 
	addi sp, sp, 0
	mv %0, s0
	mv %1, s1
	mv %2, s2
	mv %3, s3
	mv %4, s4
	mv %5, s5
	mv %6, s6
	mv %7, s7
	mv %8, s8
	mv %9, s9
	mv %10, s10
	mv %11, s11
	mv %12, ra
	li %14, 10000000
	lui %13, %hi(INF)
	sw %14, %lo(INF)(%13)
	mv s0, %0
	mv s1, %1
	mv s2, %2
	mv s3, %3
	mv s4, %4
	mv s5, %5
	mv s6, %6
	mv s7, %7
	mv s8, %8
	mv s9, %9
	mv s10, %10
	mv s11, %11
	mv ra, %12
	addi sp, sp, 0
	ret

	.globl	init
	.p2align	1
	.type	init,@function
init:
.init_.0: 
	addi sp, sp, 0
	mv %15, s0
	mv %16, s1
	mv %17, s2
	mv %18, s3
	mv %19, s4
	mv %20, s5
	mv %21, s6
	mv %22, s7
	mv %23, s8
	mv %24, s9
	mv %25, s10
	mv %26, s11
	mv %27, ra
	call _gbl_getInt
	mv %28, a0
	lui %29, %hi(n)
	sw %28, %lo(n)(%29)
	call _gbl_getInt
	mv %30, a0
	lui %31, %hi(m)
	sw %30, %lo(m)(%31)
	lw %32, n
	li %33, 4
	mul %34, %32, %33
	addi %35, %34, 4
	mv a0, %35
	call malloc
	mv %36, a0
	mv %37, %36
	sw %32, 0(%37)
	addi %38, %37, 4
	mv %39, %38
	mv %40, %39
	mv %41, zero
	j .init_.1
.init_.1: 
	blt %41, %32, .init_.18
	j .init_.2
.init_.2: 
	lui %55, %hi(a)
	sw %40, %lo(a)(%55)
	mv %56, zero
	j .init_.3
.init_.3: 
	lw %57, n
	blt %56, %57, .init_.13
	j .init_.4
.init_.4: 
	mv %59, zero
	j .init_.5
.init_.5: 
	lw %61, n
	blt %59, %61, .init_.11
	j .init_.6
.init_.6: 
	mv %85, zero
	j .init_.7
.init_.7: 
	lw %88, m
	blt %85, %88, .init_.9
	j .init_.8
.init_.8: 
	mv s0, %15
	mv s1, %16
	mv s2, %17
	mv s3, %18
	mv s4, %19
	mv s5, %20
	mv s6, %21
	mv s7, %22
	mv s8, %23
	mv s9, %24
	mv s10, %25
	mv s11, %26
	mv ra, %27
	addi sp, sp, 0
	ret
.init_.9: 
	call _gbl_getInt
	mv %89, a0
	call _gbl_getInt
	mv %90, a0
	call _gbl_getInt
	mv %91, a0
	lw %92, a
	li %95, 4
	mul %93, %89, %95
	add %94, %92, %93
	mv %96, %94
	lw %97, 0(%96)
	li %100, 4
	mul %98, %90, %100
	add %99, %97, %98
	mv %101, %99
	sw %91, 0(%101)
	j .init_.10
.init_.10: 
	addi %102, %85, 1
	mv %103, %89
	mv %104, %90
	mv %105, %91
	mv %85, %102
	j .init_.7
.init_.11: 
	lw %75, a
	li %78, 4
	mul %76, %59, %78
	add %77, %75, %76
	mv %79, %77
	lw %80, 0(%79)
	li %83, 4
	mul %81, %59, %83
	add %82, %80, %81
	mv %84, %82
	sw zero, 0(%84)
	j .init_.12
.init_.12: 
	addi %87, %59, 1
	mv %59, %87
	j .init_.5
.init_.13: 
	mv %58, zero
	j .init_.14
.init_.14: 
	lw %60, n
	blt %58, %60, .init_.16
	j .init_.15
.init_.15: 
	addi %73, %56, 1
	mv %74, %58
	mv %56, %73
	j .init_.3
.init_.16: 
	lw %62, a
	li %65, 4
	mul %63, %56, %65
	add %64, %62, %63
	mv %66, %64
	lw %67, 0(%66)
	li %70, 4
	mul %68, %58, %70
	add %69, %67, %68
	mv %71, %69
	lw %72, INF
	sw %72, 0(%71)
	j .init_.17
.init_.17: 
	addi %86, %58, 1
	mv %58, %86
	j .init_.14
.init_.18: 
	li %44, 4
	mul %42, %41, %44
	add %43, %40, %42
	mv %45, %43
	lw %46, n
	li %47, 4
	mul %48, %46, %47
	addi %49, %48, 4
	mv a0, %49
	call malloc
	mv %50, a0
	mv %51, %50
	sw %46, 0(%51)
	addi %52, %51, 4
	mv %53, %52
	sw %53, 0(%45)
	addi %54, %41, 1
	mv %41, %54
	j .init_.1

	.globl	main
	.p2align	1
	.type	main,@function
main:
.main_.0: 
	addi sp, sp, 0
	mv %106, s0
	mv %107, s1
	mv %108, s2
	mv %109, s3
	mv %110, s4
	mv %111, s5
	mv %112, s6
	mv %113, s7
	mv %114, s8
	mv %115, s9
	mv %116, s10
	mv %117, s11
	mv %118, ra
	call g_init
	call init
	mv %119, zero
	j .main_.1
.main_.1: 
	lw %120, n
	blt %119, %120, .main_.14
	j .main_.2
.main_.2: 
	mv %124, %122
	mv %125, zero
	j .main_.3
.main_.3: 
	lw %127, n
	blt %125, %127, .main_.5
	j .main_.4
.main_.4: 
	mv a0, zero
	mv s0, %106
	mv s1, %107
	mv s2, %108
	mv s3, %109
	mv s4, %110
	mv s5, %111
	mv s6, %112
	mv s7, %113
	mv s8, %114
	mv s9, %115
	mv s10, %116
	mv s11, %117
	mv ra, %118
	addi sp, sp, 0
	ret
.main_.5: 
	mv %131, zero
	j .main_.6
.main_.6: 
	lw %133, n
	blt %131, %133, .main_.9
	j .main_.7
.main_.7: 
	la %181, str2
	mv %182, %181
	mv a0, %182
	call _gbl_println
	j .main_.8
.main_.8: 
	addi %231, %125, 1
	mv %124, %131
	mv %125, %231
	j .main_.3
.main_.9: 
	lw %169, a
	li %172, 4
	mul %170, %125, %172
	add %171, %169, %170
	mv %173, %171
	lw %174, 0(%173)
	li %177, 4
	mul %175, %131, %177
	add %176, %174, %175
	mv %178, %176
	lw %179, 0(%178)
	lw %180, INF
	beq %179, %180, .main_.13
	j .main_.10
.main_.10: 
	lw %219, a
	li %222, 4
	mul %220, %125, %222
	add %221, %219, %220
	mv %223, %221
	lw %224, 0(%223)
	li %227, 4
	mul %225, %131, %227
	add %226, %224, %225
	mv %228, %226
	lw %229, 0(%228)
	mv a0, %229
	call _gbl_toString
	mv %230, a0
	mv a0, %230
	call _gbl_print
	j .main_.11
.main_.11: 
	la %232, str1
	mv %233, %232
	mv a0, %233
	call _gbl_print
	j .main_.12
.main_.12: 
	addi %234, %131, 1
	mv %131, %234
	j .main_.6
.main_.13: 
	la %217, str0
	mv %218, %217
	mv a0, %218
	call _gbl_print
	j .main_.11
.main_.14: 
	mv %121, %122
	mv %123, zero
	j .main_.15
.main_.15: 
	lw %126, n
	blt %123, %126, .main_.17
	j .main_.16
.main_.16: 
	addi %129, %119, 1
	mv %130, %123
	mv %119, %129
	mv %122, %121
	j .main_.1
.main_.17: 
	mv %128, zero
	j .main_.18
.main_.18: 
	lw %132, n
	blt %128, %132, .main_.20
	j .main_.19
.main_.19: 
	addi %168, %123, 1
	mv %121, %128
	mv %123, %168
	j .main_.15
.main_.20: 
	lw %134, a
	li %137, 4
	mul %135, %123, %137
	add %136, %134, %135
	mv %138, %136
	lw %139, 0(%138)
	li %142, 4
	mul %140, %128, %142
	add %141, %139, %140
	mv %143, %141
	lw %144, a
	li %147, 4
	mul %145, %123, %147
	add %146, %144, %145
	mv %148, %146
	lw %149, 0(%148)
	li %152, 4
	mul %150, %119, %152
	add %151, %149, %150
	mv %153, %151
	lw %154, a
	li %157, 4
	mul %155, %119, %157
	add %156, %154, %155
	mv %158, %156
	lw %159, 0(%158)
	li %162, 4
	mul %160, %128, %162
	add %161, %159, %160
	mv %163, %161
	lw %164, 0(%153)
	lw %165, 0(%163)
	add %166, %164, %165
	lw %167, 0(%143)
	bgt %167, %166, .main_.22
	j .main_.21
.main_.21: 
	addi %216, %128, 1
	mv %128, %216
	j .main_.18
.main_.22: 
	lw %183, a
	li %186, 4
	mul %184, %123, %186
	add %185, %183, %184
	mv %187, %185
	lw %188, 0(%187)
	li %191, 4
	mul %189, %128, %191
	add %190, %188, %189
	mv %192, %190
	lw %193, a
	li %196, 4
	mul %194, %123, %196
	add %195, %193, %194
	mv %197, %195
	lw %198, 0(%197)
	li %201, 4
	mul %199, %119, %201
	add %200, %198, %199
	mv %202, %200
	lw %203, a
	li %206, 4
	mul %204, %119, %206
	add %205, %203, %204
	mv %207, %205
	lw %208, 0(%207)
	li %211, 4
	mul %209, %128, %211
	add %210, %208, %209
	mv %212, %210
	lw %213, 0(%202)
	lw %214, 0(%212)
	add %215, %213, %214
	sw %215, 0(%192)
	j .main_.21

	.type	INF,@object
	.section	.data
	.globl	INF
	.p2align	2
INF:
	.zero	4
	.size	INF, 4

	.type	n,@object
	.section	.data
	.globl	n
	.p2align	2
n:
	.zero	4
	.size	n, 4

	.type	m,@object
	.section	.data
	.globl	m
	.p2align	2
m:
	.zero	4
	.size	m, 4

	.type	a,@object
	.section	.data
	.globl	a
	.p2align	2
a:
	.zero	4
	.size	a, 4

	.type	str2,@object
	.section	.rodata
str2:
	.asciz	""
	.size	str2, 1

	.type	str0,@object
	.section	.rodata
str0:
	.asciz	"-1"
	.size	str0, 3

	.type	str1,@object
	.section	.rodata
str1:
	.asciz	" "
	.size	str1, 2

