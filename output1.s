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
	addi %42, %41, 1
	bne %41, %32, .init_.18
	j .init_.2
.init_.2: 
	lui %56, %hi(a)
	sw %40, %lo(a)(%56)
	mv %57, zero
	j .init_.3
.init_.3: 
	lw %58, n
	blt %57, %58, .init_.13
	j .init_.4
.init_.4: 
	mv %60, zero
	j .init_.5
.init_.5: 
	lw %62, n
	blt %60, %62, .init_.11
	j .init_.6
.init_.6: 
	mv %86, zero
	j .init_.7
.init_.7: 
	lw %89, m
	blt %86, %89, .init_.9
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
	mv %90, a0
	call _gbl_getInt
	mv %91, a0
	call _gbl_getInt
	mv %92, a0
	lw %93, a
	li %96, 4
	mul %94, %90, %96
	add %95, %93, %94
	mv %97, %95
	lw %98, 0(%97)
	li %101, 4
	mul %99, %91, %101
	add %100, %98, %99
	mv %102, %100
	sw %92, 0(%102)
	j .init_.10
.init_.10: 
	addi %103, %86, 1
	mv %104, %90
	mv %105, %91
	mv %106, %92
	mv %86, %103
	j .init_.7
.init_.11: 
	lw %76, a
	li %79, 4
	mul %77, %60, %79
	add %78, %76, %77
	mv %80, %78
	lw %81, 0(%80)
	li %84, 4
	mul %82, %60, %84
	add %83, %81, %82
	mv %85, %83
	sw zero, 0(%85)
	j .init_.12
.init_.12: 
	addi %88, %60, 1
	mv %60, %88
	j .init_.5
.init_.13: 
	mv %59, zero
	j .init_.14
.init_.14: 
	lw %61, n
	blt %59, %61, .init_.16
	j .init_.15
.init_.15: 
	addi %74, %57, 1
	mv %75, %59
	mv %57, %74
	j .init_.3
.init_.16: 
	lw %63, a
	li %66, 4
	mul %64, %57, %66
	add %65, %63, %64
	mv %67, %65
	lw %68, 0(%67)
	li %71, 4
	mul %69, %59, %71
	add %70, %68, %69
	mv %72, %70
	lw %73, INF
	sw %73, 0(%72)
	j .init_.17
.init_.17: 
	addi %87, %59, 1
	mv %59, %87
	j .init_.14
.init_.18: 
	li %45, 4
	mul %43, %41, %45
	add %44, %37, %43
	mv %46, %44
	mv %47, %46
	lw %48, n
	li %49, 4
	mul %50, %48, %49
	addi %51, %50, 4
	mv a0, %51
	call malloc
	mv %52, a0
	mv %53, %52
	sw %48, 0(%53)
	addi %54, %53, 4
	mv %55, %54
	sw %55, 0(%47)
	mv %41, %42
	j .init_.1

	.globl	main
	.p2align	1
	.type	main,@function
main:
.main_.0: 
	addi sp, sp, 0
	mv %107, s0
	mv %108, s1
	mv %109, s2
	mv %110, s3
	mv %111, s4
	mv %112, s5
	mv %113, s6
	mv %114, s7
	mv %115, s8
	mv %116, s9
	mv %117, s10
	mv %118, s11
	mv %119, ra
	call g_init
	call init
	mv %120, zero
	j .main_.1
.main_.1: 
	lw %121, n
	blt %120, %121, .main_.14
	j .main_.2
.main_.2: 
	mv %125, %123
	mv %126, zero
	j .main_.3
.main_.3: 
	lw %128, n
	blt %126, %128, .main_.5
	j .main_.4
.main_.4: 
	mv a0, zero
	mv s0, %107
	mv s1, %108
	mv s2, %109
	mv s3, %110
	mv s4, %111
	mv s5, %112
	mv s6, %113
	mv s7, %114
	mv s8, %115
	mv s9, %116
	mv s10, %117
	mv s11, %118
	mv ra, %119
	addi sp, sp, 0
	ret
.main_.5: 
	mv %132, zero
	j .main_.6
.main_.6: 
	lw %134, n
	blt %132, %134, .main_.9
	j .main_.7
.main_.7: 
	la %182, str2
	mv %183, %182
	mv a0, %183
	call _gbl_println
	j .main_.8
.main_.8: 
	addi %232, %126, 1
	mv %125, %132
	mv %126, %232
	j .main_.3
.main_.9: 
	lw %170, a
	li %173, 4
	mul %171, %126, %173
	add %172, %170, %171
	mv %174, %172
	lw %175, 0(%174)
	li %178, 4
	mul %176, %132, %178
	add %177, %175, %176
	mv %179, %177
	lw %180, 0(%179)
	lw %181, INF
	beq %180, %181, .main_.13
	j .main_.10
.main_.10: 
	lw %220, a
	li %223, 4
	mul %221, %126, %223
	add %222, %220, %221
	mv %224, %222
	lw %225, 0(%224)
	li %228, 4
	mul %226, %132, %228
	add %227, %225, %226
	mv %229, %227
	lw %230, 0(%229)
	mv a0, %230
	call _gbl_toString
	mv %231, a0
	mv a0, %231
	call _gbl_print
	j .main_.11
.main_.11: 
	la %233, str1
	mv %234, %233
	mv a0, %234
	call _gbl_print
	j .main_.12
.main_.12: 
	addi %235, %132, 1
	mv %132, %235
	j .main_.6
.main_.13: 
	la %218, str0
	mv %219, %218
	mv a0, %219
	call _gbl_print
	j .main_.11
.main_.14: 
	mv %122, %123
	mv %124, zero
	j .main_.15
.main_.15: 
	lw %127, n
	blt %124, %127, .main_.17
	j .main_.16
.main_.16: 
	addi %130, %120, 1
	mv %131, %124
	mv %120, %130
	mv %123, %122
	j .main_.1
.main_.17: 
	mv %129, zero
	j .main_.18
.main_.18: 
	lw %133, n
	blt %129, %133, .main_.20
	j .main_.19
.main_.19: 
	addi %169, %124, 1
	mv %122, %129
	mv %124, %169
	j .main_.15
.main_.20: 
	lw %135, a
	li %138, 4
	mul %136, %124, %138
	add %137, %135, %136
	mv %139, %137
	lw %140, 0(%139)
	li %143, 4
	mul %141, %129, %143
	add %142, %140, %141
	mv %144, %142
	lw %145, a
	li %148, 4
	mul %146, %124, %148
	add %147, %145, %146
	mv %149, %147
	lw %150, 0(%149)
	li %153, 4
	mul %151, %120, %153
	add %152, %150, %151
	mv %154, %152
	lw %155, a
	li %158, 4
	mul %156, %120, %158
	add %157, %155, %156
	mv %159, %157
	lw %160, 0(%159)
	li %163, 4
	mul %161, %129, %163
	add %162, %160, %161
	mv %164, %162
	lw %165, 0(%154)
	lw %166, 0(%164)
	add %167, %165, %166
	lw %168, 0(%144)
	bgt %168, %167, .main_.22
	j .main_.21
.main_.21: 
	addi %217, %129, 1
	mv %129, %217
	j .main_.18
.main_.22: 
	lw %184, a
	li %187, 4
	mul %185, %124, %187
	add %186, %184, %185
	mv %188, %186
	lw %189, 0(%188)
	li %192, 4
	mul %190, %129, %192
	add %191, %189, %190
	mv %193, %191
	lw %194, a
	li %197, 4
	mul %195, %124, %197
	add %196, %194, %195
	mv %198, %196
	lw %199, 0(%198)
	li %202, 4
	mul %200, %120, %202
	add %201, %199, %200
	mv %203, %201
	lw %204, a
	li %207, 4
	mul %205, %120, %207
	add %206, %204, %205
	mv %208, %206
	lw %209, 0(%208)
	li %212, 4
	mul %210, %129, %212
	add %211, %209, %210
	mv %213, %211
	lw %214, 0(%203)
	lw %215, 0(%213)
	add %216, %214, %215
	sw %216, 0(%193)
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

